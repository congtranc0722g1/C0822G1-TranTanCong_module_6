package com.electronic_project.service.impl;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.model.purchase.PurchaseDetail;
import com.electronic_project.repository.ICartRepository;
import com.electronic_project.repository.IPurchaseRepository;
import com.electronic_project.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Override
    public List<ICartDto> getCart(Integer id) {
        return cartRepository.getCart(id);
    }

    @Override
    public void addCart(Integer quantity, Integer productId, Integer purchaseId) {
        cartRepository.addCart(quantity, productId, purchaseId);
    }

//    @Override
//    public void updateCart(Integer quantity, Integer productId, Integer purchaseId) {
//        cartRepository.updateCart(quantity, productId, purchaseId);
//    }

    @Override
    public Integer checkPurchase(Integer id) {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        for (int i = 0; i < purchaseList.size(); i++) {
            if (id == purchaseList.get(i).getUser().getId()){
                return purchaseList.get(i).getId();
            }
        }
        return -1;
    }

    @Override
    public Double getTotalPayment(Integer id) {
        return cartRepository.getTotalPayment(id);
    }
}
