package com.electronic_project.service.impl;

import com.electronic_project.dto.purchase.IPurchaseDetailDto;
import com.electronic_project.dto.purchase.IPurchaseHistoryDto;
import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.model.purchase.PurchaseDetail;
import com.electronic_project.repository.IPurchaseRepository;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public void addPurchase(String code, Integer purchaseStatusId, Integer userId) {
        purchaseRepository.addPurchase(code, purchaseStatusId, userId);
    }

    @Override
    public String checkCode() {
        final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int LENGTH = 10;
        Random random = new Random();
        char[] text = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            text[i] = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
        }
        return new String(text);
    }

    @Override
    public void updatePurchase(Integer purchaseStatus, String orderDate, String customerName, String customerPhone, String customerAddress, Integer id) {
        purchaseRepository.updatePurchase(purchaseStatus, orderDate, customerName, customerPhone, customerAddress, id);
    }

    @Override
    public Page<IPurchaseHistoryDto> showAll(Integer id, Pageable pageable) {
        return purchaseRepository.showAll(id, pageable);
    }

    @Override
    public List<IPurchaseDetailDto> showDetailPurchase(Integer id) {
        return purchaseRepository.showDetailPurchase(id);
    }
}
