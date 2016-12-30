package tw.bill.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by bill33 on 2016/10/16.
 */
public class OrderInfoDto {
    private Long id;
    @NotEmpty
    private String billingName;
    @NotEmpty
    private String billingAddress;
    @NotEmpty
    private String shippingName;
    @NotEmpty
    private String shippingAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "OrderInfoDto{" +
                "id=" + id +
                ", billingName='" + billingName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", shippingName='" + shippingName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                '}';
    }
}
