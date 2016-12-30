package tw.bill.service.api;

import tw.bill.dto.OrderDto;

/**
 * Created by bill33 on 2016/11/4.
 */
public interface MailService {
    void sendNotifyOrderPlaced(OrderDto orderDto);
}
