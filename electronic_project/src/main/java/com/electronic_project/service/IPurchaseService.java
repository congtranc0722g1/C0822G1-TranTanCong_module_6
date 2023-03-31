package com.electronic_project.service;

import com.electronic_project.model.purchase.Purchase;

import java.util.List;

public interface IPurchaseService {
    List<Purchase> findAll();
    void addPurchase(String code, Integer purchaseStatusId, Integer userId);

    String checkCode();

    void updatePurchase(Integer purchaseStatus, String orderDate, Integer id);
}
