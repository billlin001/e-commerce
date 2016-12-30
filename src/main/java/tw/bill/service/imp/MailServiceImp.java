package tw.bill.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tw.bill.dto.OrderDto;
import tw.bill.service.api.CartService;
import tw.bill.service.api.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bill33 on 2016/11/4.
 */
@Service
public class MailServiceImp implements MailService{
    private static Logger logger = LoggerFactory.getLogger(MailServiceImp.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendNotifyOrderPlaced(OrderDto orderDto) {
        logger.info("start sending mail");

        Context ctx = new Context();
        ctx.setVariable("order", orderDto);
        String htmlContent = templateEngine.process("mail/notify_order_placed", ctx);

        sendMail("bill.lin@thinkpower.com", "bpmabpma@gmail.com", "bpmabpma@gmail.com", "[Artstore] 感謝您完成本次的下單，以下是您這次購物明細 " + orderDto.getToken(), htmlContent);

        logger.info("end sending mail");
    }

    /**
     * No attach file support, but it's ok right now
     *
     * @param sendFrom
     * @param sendTo
     * @param replyTo
     * @param subject
     * @param htmlContent
     */
    private void sendMail(String sendFrom, String sendTo, String replyTo, String subject, String htmlContent) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setFrom(sendFrom);
            helper.setTo(sendTo);
            helper.setReplyTo(replyTo);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }

}
