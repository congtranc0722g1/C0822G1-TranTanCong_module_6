package com.electronic_project.dto.cart;

public class UpdateCartDto {
    Integer userId;
    Integer purchaseStatusId;
    String customerName;
    String customerPhone;
    String customerAddress;

    public UpdateCartDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPurchaseStatusId() {
        return purchaseStatusId;
    }

    public void setPurchaseStatusId(Integer purchaseStatusId) {
        this.purchaseStatusId = purchaseStatusId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
