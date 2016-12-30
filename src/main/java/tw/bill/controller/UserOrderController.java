package tw.bill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.dto.Cart;
import tw.bill.dto.OrderDto;
import tw.bill.dto.OrderInfoDto;
import tw.bill.dto.Pay2GoDto;
import tw.bill.service.api.CartService;
import tw.bill.service.api.OrderService;
import tw.bill.service.api.Pay2GoService;
import tw.bill.service.security.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by bill33 on 2016/10/16.
 */
@Controller
@RequestMapping("/user/orders")
@SessionAttributes("currentCart")
public class UserOrderController {
    Logger logger = LoggerFactory.getLogger(UserOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private Pay2GoService pay2GoService;

    @PostMapping("/create")
    public String createOrder(@Valid @ModelAttribute("orderInfoDto") OrderInfoDto orderInfoDto,
                              BindingResult bindingResult, @AuthenticationPrincipal CurrentUser currentUser) {
        if(bindingResult.hasErrors()) {
            return "cart/checkout";
        }

        Cart cart = cartService.getCurrentCart();
        OrderDto orderDto = orderService.create(currentUser.getUser(), cart, orderInfoDto);
        return "redirect:/user/orders/" + orderDto.getToken();
    }

    @RequestMapping("/{orderToken}")
    public ModelAndView showOrder(@PathVariable("orderToken") String orderToken, HttpServletRequest req) {
        logger.info(orderToken);
        ModelAndView modelAndView = new ModelAndView("user/order/show");
        modelAndView.addObject("order", orderService.fetchOrderByToken(orderToken));
        modelAndView.addObject("pay2Go", pay2GoService.fetchPay2GoSetup(orderToken));
        return modelAndView;
    }


    @RequestMapping("/{orderToken}/payByCredit")
    public String payByCredit(@PathVariable("orderToken") String orderToken) {
        logger.info("orderToken: {}", orderToken);
        OrderDto orderDto = orderService.payByCredit(orderToken);
        return "redirect:/user/orders/" + orderDto.getToken();
    }

    @RequestMapping("")
    public ModelAndView listOrders(@AuthenticationPrincipal CurrentUser currentUser) {
        List<OrderDto> orderDtos = orderService.findByUserId(currentUser.getUser().getId());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/order/list");
        mv.addObject("orders", orderDtos);
        return mv;
    }

    // TODO need an approvement of pay2go credit card
    @RequestMapping("/{orderToken}/paySuccessByPay2GoCreditCard")
    public String paySuccessByPay2Go(@PathVariable("orderToken") String orderToken, HttpServletRequest req) {
        logger.info("orderToken: {}", orderToken);
        OrderDto orderDto = orderService.payByCredit(orderToken);
        return "redirect:/user/orders/" + orderDto.getToken();
    }
}
