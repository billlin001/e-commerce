package tw.bill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.service.api.OrderService;

import javax.websocket.server.PathParam;

/**
 * Created by bill33 on 2016/10/30.
 */
@Controller
@SessionAttributes("currentCart")
@RequestMapping("/admin/orders")
public class AdminOrderController {
    Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping("")
    public ModelAndView listOrders() {
        return new ModelAndView("admin/order/list", "orders", orderService.findAll());
    }

    @RequestMapping("/{orderId}")
    public ModelAndView showOrder(@PathVariable("orderId") Long orderId) {
        logger.info("orderId: {}", orderId);
        return new ModelAndView("admin/order/show", "order", orderService.findById(orderId));
    }

    @RequestMapping("/{orderId}/cancel")
    public ModelAndView cancelOrder(@PathVariable("orderId") Long orderId) {
        logger.info("orderId: {}", orderId);
        return new ModelAndView("admin/order/show", "order", orderService.cancelOrder(orderId));
    }

    @RequestMapping("/{orderId}/shipping")
    public ModelAndView shippingOrder(@PathVariable("orderId") Long orderId) {
        logger.info("orderId: {}", orderId);
        return new ModelAndView("admin/order/show", "order", orderService.shippingOrder(orderId));
    }

    @RequestMapping("/{orderId}/shipped")
    public ModelAndView shippedOrder(@PathVariable("orderId") Long orderId) {
        logger.info("orderId: {}", orderId);
        return new ModelAndView("admin/order/show", "order", orderService.shippedOrder(orderId));
    }

    @RequestMapping("/{orderId}/return_good")
    public ModelAndView returnGood(@PathVariable("orderId") Long orderId) {
        logger.info("orderId: {}", orderId);
        return new ModelAndView("admin/order/show", "order", orderService.returnGood(orderId));
    }

}
