package tw.bill.service.imp;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.bill.convertor.OrderStateConvertor;
import tw.bill.domain.*;
import tw.bill.dto.Cart;
import tw.bill.dto.OrderDto;
import tw.bill.dto.OrderInfoDto;
import tw.bill.respository.OrderRepository;
import tw.bill.respository.ProductRespository;
import tw.bill.respository.UserRepository;
import tw.bill.service.api.MailService;
import tw.bill.service.api.OrderService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by bill33 on 2016/10/16.
 */
@Service
public class OrderServiceImp implements OrderService{
    Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public OrderDto create(User user, Cart cart, OrderInfoDto orderInfoDto) {
        String token = UUID.randomUUID().toString();

        Order order = new Order();
        order.setTotal(cart.getTotalPrice());
        order.setToken(UUID.randomUUID().toString());
        order.setCurrentState(OrderState.ORDER_PLACED);
        order.setUser(user);
        user.addOrder(order);

        OrderInfo orderInfo = modelMapper.map(orderInfoDto, OrderInfo.class);
        orderInfo.setOrder(order);
        order.setOrderInfo(orderInfo);

        cart.getCartItems().forEach(cartItem -> {
            Product product = productRespository.findOne(cartItem.getItemId());
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRespository.save(product);

            OrderItem orderItem = modelMapper.map(cartItem, OrderItem.class);
            orderItem.setId(null);
            orderItem.setProductName(cartItem.getTitle());
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getItemId());
            order.addOrderItem(orderItem);
        });

        logger.info("order: {}", order);
        cart.clear();

        userRepository.save(user);


        OrderDto orderDto = modelMapper.map(orderRepository.save(order), OrderDto.class);
//        mailService.sendNotifyOrderPlaced(orderDto);

//        try {
//            Thread.sleep(30 * 1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return orderDto;
    }

    @Override
    public OrderDto fetchOrderByToken(String orderToken) {
        Order order = orderRepository.findByToken(orderToken);
        logger.info("order: {}", order);
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        logger.info("orderDto: {}", orderDto);
        return orderDto;
    }

    @Override
    public OrderDto payByCredit(String orderToken) {
        Order order = orderRepository.findByToken(orderToken);
//        order.setPay(true);
        order.setPayMethod("creditCard");
        makePayment(order);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    public void makePayment(Order order) {
        order.setPay(true);
        order.setCurrentState(OrderState.PAID);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> findAllOrdersForCurrentUser(User currentUser) {
        User user = userRepository.findOne(currentUser.getId());
        List<OrderDto> orderDtos = user.getOrders().stream().
                map(item -> modelMapper.map(item, OrderDto.class)).
                collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public OrderDto findById(Long orderId) {
        return modelMapper.map(orderRepository.findOne(orderId), OrderDto.class);
    }

    @Override
    public List<OrderDto> findByUserId(Long id) {
        List<OrderDto> orderDtos = orderRepository.findByUserId(id).stream().
                map(item -> modelMapper.map(item, OrderDto.class)).
                collect(Collectors.toList());

        return orderDtos;
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderDto> orderDtos = orderRepository.findAllByOrderByIdDesc()
                .stream().map(item -> {
                    OrderDto dto = modelMapper.map(item, OrderDto.class);
                    return dto;
                })
                .collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    @Transactional
    public OrderDto cancelOrder(Long id) {
        return changeState(id, OrderState.ORDER_CANCELED);
    }

    @Override
    @Transactional
    public OrderDto shippingOrder(Long id) {
        return changeState(id, OrderState.SHIPPING);
    }

    @Override
    @Transactional
    public OrderDto shippedOrder(Long id) {
        return changeState(id, OrderState.SHIPPED);
    }

    @Override
    @Transactional
    public OrderDto returnGood(Long id) {
        return changeState(id, OrderState.GOOD_RETURNED);
    }

    private OrderDto changeState(Long id, OrderState state) {
        Order order = orderRepository.findOne(id);
        order.setCurrentState(state);
        addProductQuantity(order, state);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    private void addProductQuantity(Order order, OrderState state) {
        if(state == OrderState.ORDER_CANCELED || state == OrderState.GOOD_RETURNED) {
            order.getOrderItems().stream()
                    .forEach(orderItem -> {
                        Product product = productRespository.findOne(orderItem.getProductId());
                        product.setQuantity(product.getQuantity() + orderItem.getQuantity());
                        productRespository.save(product);
                    });
        }
    }
}
