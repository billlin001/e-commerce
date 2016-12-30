package tw.bill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import tw.bill.property.Pay2GoProperties;
import tw.bill.property.StorageProperties;
import tw.bill.service.api.MailService;
import tw.bill.service.api.UserService;

/**
 * Created by bill33 on 2016/9/11.
 */
@SpringBootApplication(exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    /**
     * Test properties
     */
//    @Bean
//    public CommandLineRunner testProperties(Pay2GoProperties pay2GoProperties) {
//        return (args) -> {
//            logger.info("merchant_id: {}", pay2GoProperties.getMerchantId());
//            logger.info("hash_key: {}", pay2GoProperties.getHashKey());
//            logger.info("hash_iv: {}", pay2GoProperties.getHashIv());
//            logger.info("service_url: {}", pay2GoProperties.getServiceUrl());
//
//        };
//    }

//    /**
//     * Test if tranctional works
//     * Transaction seem work in this spring boot version
//     */
//    @Bean
//    public CommandLineRunner loadData(UserService userService) {
//        return (args) -> {
//            try{
//                logger.info("start test transaction");
//                userService.testTransactional();
//                logger.info("finish test transaction");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        };
//    }

/**
 * Test email service
 */
//    @Bean
//    public CommandLineRunner loadData(MailService mailService) {
//        return (args) -> {
//            logger.info("test sending mail");
////            mailService.sendMail();
//            logger.info("finish sending mail");
//        };
//    }
}
