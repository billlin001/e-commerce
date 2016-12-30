package tw.bill.service.api;

import tw.bill.dto.Pay2GoDto;

/**
 * Created by bill33 on 2016/11/6.
 */
public interface Pay2GoService {
    public Pay2GoDto fetchPay2GoSetup(String orderToken);
}
