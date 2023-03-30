package com.electronic_project.service.impl;

import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.repository.IPurchaseRepository;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addPurchase(String code, String orderDate, Integer purchaseStatusId, Integer userId) {
        purchaseRepository.addPurchase(code, orderDate, purchaseStatusId, userId);
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
}
