package com.electronic_project.controller;

import com.electronic_project.dto.cart.CartDto;
import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.purchase.PurchaseDetail;
import com.electronic_project.service.ICartService;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping("/list")
    private ResponseEntity<List<ICartDto>> getCart(@RequestParam(value = "id") Integer id) {
        List<ICartDto> cartList = cartService.getCart(id);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<?> addCart(@RequestBody CartDto cartDto) {
        Integer purchaseId = cartService.checkPurchase(cartDto.getUserId());
        PurchaseDetail purchaseDetail = cartService.findPurchase(cartDto.getProductId(), purchaseId);
        if (purchaseId != -1) {
            if (purchaseDetail == null){
                cartService.addCart(cartDto.getQuantity(), cartDto.getProductId(), purchaseId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            if (purchaseDetail != null) {
                Integer newQuantity = purchaseDetail.getQuantity() + cartDto.getQuantity();
                cartService.updateCart(newQuantity, cartDto.getProductId(), purchaseId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        if (purchaseId == -1) {
            String code = purchaseService.checkCode();
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDateTime = currentDateTime.format(formatter);
            purchaseService.addPurchase(code, formattedDateTime, 1, cartDto.getUserId());
            Integer purchaseIdNew = cartService.checkPurchase(cartDto.getUserId());
            cartService.addCart(cartDto.getQuantity(), cartDto.getProductId(), purchaseIdNew);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/total-payment")
    private ResponseEntity<Double> getTotalPayment(@RequestParam(value = "id") Integer id) {
        Double totalPayment = cartService.getTotalPayment(id);
        return new ResponseEntity<>(totalPayment, HttpStatus.OK);
    }

    @PostMapping("/delete")
    private ResponseEntity<?> deletePurchaseDetail(@RequestBody CartDto cartDto){
        Integer purchaseId = cartService.checkPurchase(cartDto.getUserId());
        cartService.deletePurchaseDetail(cartDto.getProductId(), purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCart(@RequestBody CartDto cartDto){
        Integer purchaseId = cartService.checkPurchase(cartDto.getUserId());
        cartService.updateCart(cartDto.getQuantity(), cartDto.getProductId(), purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
