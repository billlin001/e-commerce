package tw.bill.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tw.bill.domain.Product;
import tw.bill.domain.Role;
import tw.bill.dto.UserCreateForm;
import tw.bill.respository.ProductRespository;
import tw.bill.service.api.UserService;

/**
 * Created by bill33 on 2016/9/25.
 */
@Component
public class MockUserProvider  implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(MockUserProvider.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRespository productRespository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("user boostartp start");

//        loadUserInfo();
//        loadProductInfo();

        logger.info("user bootstrap end");
    }

    private void loadUserInfo() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserCreateForm user1 = new UserCreateForm();
        user1.setEmail("admin");
        user1.setPassword("1234");
        user1.setRole(Role.ADMIN);
        userService.create(user1);

        UserCreateForm user2 = new UserCreateForm();
        user2.setEmail("user");
        user2.setPassword("1234");
        user2.setRole(Role.USER);
        userService.create(user2);

        UserCreateForm user3 = new UserCreateForm();
        user3.setEmail("user2");
        user3.setPassword("1234");
        user3.setRole(Role.USER);
        userService.create(user3);
    }

    private void loadProductInfo() {
        Product product1 = new Product();
        product1.setTitle("Test1");
        product1.setDescription("Description 1");
        product1.setQuantity(20);
        product1.setPrice(100);
        product1.setFileName("1156e919-9e6f-47ed-8c95-eeb3989459be");
        productRespository.save(product1);

        Product product2 = new Product();
        product2.setTitle("Test1");
        product2.setDescription("Description 1");
        product2.setQuantity(30);
        product2.setPrice(200);
        product2.setFileName("99b71dce-1aba-4ce3-b072-018fa1b938c2");
        productRespository.save(product2);

    }
}
