package tw.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.service.api.ProductService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by bill33 on 2016/9/13.
 */
@Controller
@SessionAttributes("currentCart")
public class HomeController {
    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public ModelAndView index(HttpSession session, Principal principal) {
        Boolean isFirstLogin = getIsFisrtLogin(session, principal);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("products", productService.findProducts());
        modelAndView.addObject("isFirstLogin", isFirstLogin);

        return modelAndView;
    }

    private Boolean getIsFisrtLogin(HttpSession session, Principal principal) {
        Boolean isFirstLogin = false;
        if(principal != null) {
            Boolean isFirstLoginInSession = (Boolean)session.getAttribute("isFirstLogin");
            if(isFirstLoginInSession == null) {
                isFirstLogin = true;
                session.setAttribute("isFirstLogin", false);
            }
        }
        return isFirstLogin;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
