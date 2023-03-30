package com.electronic_project.service;

import com.electronic_project.model.purchase.Purchase;

import java.util.List;

public interface IPurchaseService {
    List<Purchase> findAll();
    void addPurchase(String code, String orderDate, Integer purchaseStatusId, Integer userId);

    String checkCode();
}
