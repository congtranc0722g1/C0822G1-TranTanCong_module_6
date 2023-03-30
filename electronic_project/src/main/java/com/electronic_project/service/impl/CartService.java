package com.electronic_project.service.impl;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.model.purchase.PurchaseDetail;
import com.electronic_project.repository.ICartRepository;
import com.electronic_project.repository.IPurchaseRepository;
import com.electronic_project.service.ICartService;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IPurchaseService purchaseService;

    @Override
    public List<ICartDto> getCart(Integer id) {
        return cartRepository.getCart(id);
    }

    @Override
    public void addCart(Integer quantity, Integer productId, Integer purchaseId) {
        cartRepository.addCart(quantity, productId, purchaseId);
    }

    @Override
    public void updateCart(Integer quantity, Integer productId, Integer purchaseId) {
        cartRepository.updateCart(quantity, productId, purchaseId);
    }

    @Override
    public Integer checkPurchase(Integer id) {
        List<Purchase> purchaseList = purchaseService.findAll();
        for (int i = 0; i < purchaseList.size(); i++) {
            if (id == purchaseList.get(i).getUser().getId() && purchaseList.get(i).getPurchaseStatus().getId() == 1){
                return purchaseList.get(i).getId();
            }
        }
        return -1;
    }

    @Override
    public Double getTotalPayment(Integer id) {
        return cartRepository.getTotalPayment(id);
    }

    @Override
    public PurchaseDetail findPurchase(Integer productId, Integer purchaseId) {
        return cartRepository.findPurchase(productId, purchaseId);
    }

    @Override
    public void deletePurchaseDetail(Integer productId, Integer purchaseId) {
        cartRepository.deletePurchaseDetail(productId, purchaseId);
    }
}
