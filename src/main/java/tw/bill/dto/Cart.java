package tw.bill.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bill33 on 2016/9/28.
 */

@Component("currentCart")
@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart{

    private List<CartItem> cartItems = new ArrayList<>();
    private Integer totalPrice;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public int itemSize() {
        return cartItems.size();
    }

    public int getTotalPrice() {
        if(CollectionUtils.isEmpty(cartItems)) return 0;
        totalPrice = cartItems.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum();
        return totalPrice;
    }

    public void clear() {
        totalPrice = 0;
        cartItems.clear();
    }


    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}


