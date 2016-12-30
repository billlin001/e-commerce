package tw.bill.service.api;

import tw.bill.dto.Cart;
import tw.bill.dto.CartItem;

import java.util.List;

/**
 * Created by bill33 on 2016/9/28.
 */
public interface CartService {
    void addProductToCart(Long productId);

    List<CartItem> findAllCartItemsInCart();

    Cart getCurrentCart();

    void clearAllItem();

    void deleteItemByItemId(Long itemId);

    void updateCartItem(Cart cart);

    void updateItemByItemId(Long productId);
}
