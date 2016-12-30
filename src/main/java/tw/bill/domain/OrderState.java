package tw.bill.domain;

/**
 * Created by bill33 on 2016/10/29.
 */
public enum OrderState {
    ORDER_PLACED("order_placed"),
    PAID("paid"),
    SHIPPING("shipping"),
    SHIPPED("shipped"),
    ORDER_CANCELED("ordder_canceled"),
    GOOD_RETURNED("good_returned");

    private final String value;
    OrderState(String value) { this.value = value; }
    public String getValue() { return value; }
}
