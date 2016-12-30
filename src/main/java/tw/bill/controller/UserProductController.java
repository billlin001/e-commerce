package tw.bill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.dto.Cart;
import tw.bill.service.api.CartService;
import tw.bill.service.api.ProductService;
import tw.bill.service.api.StorageService;

/**
 * Created by bill33 on 2016/9/9.
 */
@Controller
@SessionAttributes("currentCart")
public class UserProductController {
    private static Logger log = LoggerFactory.getLogger(UserProductController.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        if (StringUtils.isEmpty(filename)) {
            return ResponseEntity
                    .ok().header("", "").body(null);
        }

        log.info("fileName:{}", filename);
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @GetMapping("/products/{productId}")
    public ModelAndView showProduct(@PathVariable("productId") Long productId) {
        return new ModelAndView("user/product/show", "product", productService.findById(productId));
    }

    @RequestMapping("/products/{productId}/addToCart")
    public ModelAndView addToCart(@PathVariable("productId") Long productId) {
        cartService.addProductToCart(productId);
        return new ModelAndView("user/product/show", "product", productService.findById(productId));
    }

    @ModelAttribute("currentCart")
    public Cart showCurrentCart(Cart aaa) {
        return cartService.getCurrentCart();
    }
}
