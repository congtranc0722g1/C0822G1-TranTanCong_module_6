package com.electronic_project.controller;

import com.electronic_project.dto.cart.CartDto;
import com.electronic_project.dto.cart.UpdateCartDto;
import com.electronic_project.service.ICartService;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin("*")
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IPurchaseService purchaseService;

    @PutMapping("/update")
    private ResponseEntity<?> updatePurchase(@RequestBody UpdateCartDto updateCartDto){
        Integer purchaseId = cartService.checkPurchase(updateCartDto.getUserId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentDateTime.format(formatter);
        purchaseService.updatePurchase(updateCartDto.getPurchaseStatusId(), formattedDateTime, purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
