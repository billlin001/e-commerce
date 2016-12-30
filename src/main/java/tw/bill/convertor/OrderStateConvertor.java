package tw.bill.convertor;

import tw.bill.domain.OrderState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bill33 on 2016/10/31.
 */
public class OrderStateConvertor {
    private static Map<OrderState, String> orderMapping = new HashMap<>();

    static {
        orderMapping.put(OrderState.ORDER_PLACED, "訂貨");
        orderMapping.put(OrderState.PAID, "已付款");
        orderMapping.put(OrderState.SHIPPING, "出貨中");
        orderMapping.put(OrderState.SHIPPED, "已到貨");
        orderMapping.put(OrderState.ORDER_CANCELED, "取消訂單");
        orderMapping.put(OrderState.GOOD_RETURNED, "退貨");
    }

    public static String convert(OrderState state) {
        return orderMapping.get(state);
    }
}
