package tw.bill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.dto.ProductDto;
import tw.bill.service.api.ProductService;
import tw.bill.service.api.StorageService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by bill33 on 2016/9/9.
 */
@Controller
@SessionAttributes("currentCart")
@RequestMapping("/admin")
public class AdminProductController {
    private static Logger log = LoggerFactory.getLogger(AdminProductController.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public ModelAndView listProducts(HttpSession session) {
        Boolean isFirstLogin = (Boolean)session.getAttribute("isFirstLogin");
        if(isFirstLogin == null) isFirstLogin = true;
        else session.setAttribute("isFirstLogin", false);
        System.out.println(isFirstLogin);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/product/list");
        modelAndView.addObject("products", productService.findProducts());
        modelAndView.addObject("isFirstLogin", isFirstLogin);

        return modelAndView;
    }

    @RequestMapping("/products/new")
    public ModelAndView newProduct() {
        return new ModelAndView("admin/product/new", "product", new ProductDto());
    }

    @RequestMapping(value = "/products/create", method = RequestMethod.POST)
    public String createProduct(@Valid ProductDto product, @RequestParam("uploadFile") MultipartFile file) {
        if(!file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            product.setFileName(uuid.toString());
            storageService.store(uuid.toString(), file);
        }
        productService.createProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/products/{productId}/edit")
    public ModelAndView editProduct(@PathVariable("productId") Long productId) {
        return new ModelAndView("admin/product/edit",
                "product", productService.findById(productId));
    }

    @PostMapping("/products/{productId}/update")
    public String updateProduct(@PathVariable("productId") Long productId, @Valid ProductDto product, @RequestParam("uploadFile") MultipartFile file) {
        if(!file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            product.setFileName(uuid.toString());
            storageService.store(uuid.toString(), file);
        }
        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleResourceNotFoundException(Exception ex) {
        return new ModelAndView("error", "exception", ex);
    }
}
