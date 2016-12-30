package tw.bill.domain;

import javax.persistence.*;

/**
 * Created by bill33 on 2016/10/2.
 */
@Entity
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private int version;

    @OneToOne
    @JoinColumn(name = "ec_order_id")
    private Order order;

    private String billingName;

    private String billingAddress;

    private String shippingName;

    private String shippingAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", billingName='" + billingName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", shippingName='" + shippingName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                '}';
    }
}
