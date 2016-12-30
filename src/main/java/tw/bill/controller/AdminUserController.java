package tw.bill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import tw.bill.service.api.UserService;

/**
 * Created by bill33 on 2016/9/25.
 */
@Controller
@SessionAttributes("currentCart")
@RequestMapping("/admin")
public class AdminUserController {
    private static Logger log = LoggerFactory.getLogger(AdminUserController.class);

    private UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView listUsers() {
        return new ModelAndView("admin/user/list", "users", userService.getAllUsers());
    }

    @RequestMapping("/users/changeRole/{userId}")
    public String changeRole(@PathVariable Long userId) {
        log.info("userId: {}", userId);
        userService.changeRole(userId);
        return "redirect:/admin/users";
    }
}

