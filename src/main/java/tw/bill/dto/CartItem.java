package tw.bill.dto;

import tw.bill.domain.Product;

/**
 * Created by bill33 on 2016/9/28.
 */
//@Entity
public class CartItem{
    private Long itemId;
    private String title;
    private String description;
    private Integer quantity;
    private Integer maxQuantity;
    private Integer storeQuantity;
    private Integer price;
    private String fileName;

    public CartItem() {
    }

    public CartItem(Product product) {
        this.itemId = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.quantity = 1;
        this.maxQuantity = product.getQuantity();
        this.storeQuantity = product.getQuantity();
        this.price = product.getPrice();
        this.fileName = product.getFileName();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(Integer storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return itemId != null ? itemId.equals(cartItem.itemId) : cartItem.itemId == null;

    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", maxQuantity=" + maxQuantity +
                ", storeQuantity=" + storeQuantity +
                ", price=" + price +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
