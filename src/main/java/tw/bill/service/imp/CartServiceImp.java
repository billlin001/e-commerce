package tw.bill.service.imp;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.bill.domain.Product;
import tw.bill.dto.Cart;
import tw.bill.dto.CartItem;
import tw.bill.respository.ProductRespository;
import tw.bill.service.api.CartService;

import java.util.List;

/**
 * Created by bill33 on 2016/9/28.
 */
@Service
public class CartServiceImp implements CartService {
    Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private Cart currentCart;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void addProductToCart(Long productId) {
        Product product = productRespository.findOne(productId);
        boolean isExist = currentCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItemId().longValue() == product.getId().longValue())
                .findAny().isPresent();
        if( ! isExist )
            currentCart.addCartItem(new CartItem(product));
    }

    @Override
    public List<CartItem> findAllCartItemsInCart() {
        return currentCart.getCartItems();
    }

    @Override
    public Cart getCurrentCart() {
        return currentCart;
    }

    @Override
    public void clearAllItem() {
        currentCart.clear();
    }

    @Override
    public void deleteItemByItemId(Long productId) {
        Product product = productRespository.findOne(productId);
        currentCart.removeCartItem(new CartItem(product));
    }

    @Override
    public void updateCartItem(Cart inputCart) {
        logger.info("enter...");
        logger.info("input cart : {}", inputCart);
        logger.info("old shipping cart: {}", currentCart);

        currentCart.getCartItems().forEach(currentCartItem -> {
            CartItem matchInputCartItem = inputCart.getCartItems().stream()
                    .filter(inputCartitem -> inputCartitem.getItemId().longValue() == currentCartItem.getItemId().longValue())
                    .findFirst().get();
            currentCartItem.setQuantity(matchInputCartItem.getQuantity());
        });


        logger.info("change shipping cart: {}", currentCart);
    }

    @Override
    public void updateItemByItemId(Long productId) {

    }
}
