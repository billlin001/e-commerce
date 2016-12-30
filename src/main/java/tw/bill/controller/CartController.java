package tw.bill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.dto.Cart;
import tw.bill.dto.CartItem;
import tw.bill.dto.OrderInfoDto;
import tw.bill.service.api.CartService;

/**
 * Created by bill33 on 2016/9/28.
 */
@Controller
@RequestMapping("/shoppingCart")
@SessionAttributes("currentCart")
public class CartController {
    Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @GetMapping
    public String showShoppingCart() {
        return "cart/list";
    }

    @RequestMapping("/clear")
    public String clearShoppingCart() {
        cartService.clearAllItem();
        return "cart/list";
    }

    @RequestMapping(value="/update", params={"changeQuantity"})
    public String changeQuantity(Cart shoppingCart) {
        logger.info("enter..");
        cartService.updateCartItem(shoppingCart);
        return "cart/list";
    }

    @RequestMapping("/{productId}/remove")
    public String clearShoppingCart(@PathVariable("productId") Long productId) {
        cartService.deleteItemByItemId(productId);
        return "cart/list";
    }

//    @RequestMapping(value = "/currentPrice")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Cart getCurrentPrice(@RequestBody CartItem cartItem) {
//        return cartService.updateCartItem(cartItem);
//    }

    @RequestMapping("/checkout")
    public ModelAndView checkout(){
        return new ModelAndView("cart/checkout", "orderInfoDto", new OrderInfoDto());
    }
}
