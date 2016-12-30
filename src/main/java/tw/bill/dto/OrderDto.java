package tw.bill.dto;

import tw.bill.convertor.OrderStateConvertor;
import tw.bill.domain.OrderState;
import tw.bill.domain.User;

import javax.persistence.PrePersist;
import java.util.Date;
import java.util.List;

/**
 * Created by bill33 on 2016/10/16.
 */
public class OrderDto {
    private Long id;
    private User user;
    private int total;
    private List<OrderItemDto> orderItems;
    private OrderInfoDto orderInfo;
    private String token;
    private boolean isPay;
    private String payMethod;

    private OrderState currentState;
    private String status;

    private Date createTime;
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderInfoDto getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoDto orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
        this.status = OrderStateConvertor.convert(currentState);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", total=" + total +
                ", orderItems=" + orderItems +
                ", orderInfo=" + orderInfo +
                ", token='" + token + '\'' +
                ", isPay=" + isPay +
                ", payMethod='" + payMethod + '\'' +
                '}';
    }
}
