package com.electronic_project.service;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.purchase.PurchaseDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService {

    List<ICartDto> getCart(Integer id);

    void addCart(Integer quantity, Integer productId, Integer purchaseId);

    void updateCart(Integer quantity, Integer productId, Integer purchaseId);

    Integer checkPurchase(Integer id);

    Double getTotalPayment(Integer id);

    PurchaseDetail findPurchase(Integer productId, Integer purchaseId);

    void deletePurchaseDetail(Integer productId, Integer purchaseId);
}
