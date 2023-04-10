package com.electronic_project.service;

import com.electronic_project.dto.purchase.IPurchaseDetailDto;
import com.electronic_project.dto.purchase.IPurchaseHistoryDto;
import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.model.purchase.PurchaseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPurchaseService {
    List<Purchase> findAll();
    void addPurchase(String code, Integer purchaseStatusId, Integer userId);

    String checkCode();

    void updatePurchase(Integer purchaseStatus, String orderDate, String customerName, String customerPhone, String customerAddress, Integer id);

    Page<IPurchaseHistoryDto> showAll(Integer id, Pageable pageable);

    List<IPurchaseDetailDto> showDetailPurchase(@Param("id") Integer id);
}
