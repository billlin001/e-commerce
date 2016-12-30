package tw.bill.service.imp;

import org.apache.commons.lang.time.DateUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tw.bill.domain.Order;
import tw.bill.dto.Pay2GoDto;
import tw.bill.property.Pay2GoProperties;
import tw.bill.respository.OrderRepository;
import tw.bill.service.api.Pay2GoService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bill33 on 2016/11/6.
 */
@Service
public class Pay2GoServiceImp implements Pay2GoService {
    private static Logger logger = LoggerFactory.getLogger(Pay2GoServiceImp.class);

    @Autowired
    private Pay2GoProperties pay2GoProperties;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StrongPasswordEncryptor strongPasswordEncryptor;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    public Pay2GoDto fetchPay2GoSetup(String orderToken) {
        Order order = orderRepository.findByToken(orderToken);

        Pay2GoDto pay2GoDto = new Pay2GoDto();
        pay2GoDto.setMerchantId(pay2GoProperties.getMerchantId());
        pay2GoDto.setHashKey(pay2GoProperties.getHashKey());
        pay2GoDto.setHashIv(pay2GoProperties.getHashIv());
        pay2GoDto.setServiceUrl(pay2GoProperties.getServiceUrl());

        pay2GoDto.setMerchantOrderNo(order.getId() + simpleDateFormat.format(new Date()));
        pay2GoDto.setTimeStamp(new Date().getTime());
        pay2GoDto.setAmt(order.getTotal());

        pay2GoDto.setReturnURL(String.format("http://127.0.0.1/user/orders/%s/paySuccessByPay2GoCreditCard", order.getToken()));
        pay2GoDto.setCheckValue(genCheckValue(pay2GoDto));

        pay2GoDto.setEmail("bill.lin@thinkpower.com.tw");

        return pay2GoDto;
    }

    private String genCheckValue(Pay2GoDto pay2GoDto) {
        String paramValue = String.format("HashKey=%s&Amt=%d&MerchantID=%s&MerchantOrderNo=%s&TimeStamp=%d&Version=%s&HashIV=%s",
                pay2GoDto.getHashKey(),
                pay2GoDto.getAmt(),
                pay2GoDto.getMerchantId(),
                pay2GoDto.getMerchantOrderNo(),
                pay2GoDto.getTimeStamp(),
                pay2GoDto.getVersion(),
                pay2GoDto.getHashIv());
        logger.info("paramValue: {}", paramValue);

        String checkValue = hashBySha256(paramValue);

        return checkValue;
    }

    private static String hashBySha256(String paramValue) {
        String checkValue = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(paramValue.getBytes(StandardCharsets.UTF_8));
            checkValue = String.format("%064x", new java.math.BigInteger(1, hash));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return checkValue.toUpperCase();
    }

    public static void main(String[] args) {
        String paramValue = "HashKey=84ehaXPMwaEwhV5EMkh2C4CenRYDByVJ&Amt=100&MerchantID=114128529&MerchantOrderNo=3s20161107&TimeStamp=1478452060051&Version=1.2&HashIV=Ca1M7QzD0e9i9fXW";
        String actualCheckValue = hashBySha256(paramValue);
        System.out.println(actualCheckValue);
    }
}
