package com.electronic_project.dto.purchase;

public interface IPurchaseHistoryDto {
    Integer getId();
    String getCode();
    String getOrderDate();
    String getStatus();
    Double getTotal();
    String getCustomerName();
    String getCustomerPhone();
    String getCustomerAddress();
}
