package com.electronic_project.dto.cart;

public class UpdateCartDto {
    Integer userId;
    Integer purchaseStatusId;

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
}
