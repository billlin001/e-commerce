package tw.bill.service.api;

import tw.bill.domain.User;
import tw.bill.dto.Cart;
import tw.bill.dto.OrderDto;
import tw.bill.dto.OrderInfoDto;

import java.util.List;

/**
 * Created by bill33 on 2016/10/16.
 */
public interface OrderService {
    OrderDto create(User user, Cart cart, OrderInfoDto order);
    OrderDto fetchOrderByToken(String orderToken);
    OrderDto payByCredit(String orderToken);

    List<OrderDto> findAllOrdersForCurrentUser(User user);

    OrderDto findById(Long orderId);

    List<OrderDto> findByUserId(Long id);

    List<OrderDto> findAll();

    OrderDto cancelOrder(Long id);

    OrderDto shippingOrder(Long id);

    OrderDto shippedOrder(Long id);

    OrderDto returnGood(Long id);
}
