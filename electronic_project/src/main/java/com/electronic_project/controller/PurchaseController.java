package com.electronic_project.controller;

import com.electronic_project.dto.cart.CartDto;
import com.electronic_project.dto.cart.UpdateCartDto;
import com.electronic_project.dto.purchase.IPurchaseDetailDto;
import com.electronic_project.dto.purchase.IPurchaseHistoryDto;
import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.model.purchase.PurchaseDetail;
import com.electronic_project.service.ICartService;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        purchaseService.updatePurchase(updateCartDto.getPurchaseStatusId(), formattedDateTime, updateCartDto.getCustomerName(), updateCartDto.getCustomerPhone(), updateCartDto.getCustomerAddress(), purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list-purchase")
    private ResponseEntity<Page<IPurchaseHistoryDto>> showAll(@RequestParam(value = "id") Integer id, @PageableDefault(size = 5)Pageable pageable){
        Page<IPurchaseHistoryDto> purchases = purchaseService.showAll(id, pageable);
        return new ResponseEntity<>(purchases, HttpStatus.OK);

    }

    @GetMapping("/list-purchase-detail")
    private ResponseEntity<List<IPurchaseDetailDto>> showDetailPurchase(@RequestParam(value = "id") Integer id){
        List<IPurchaseDetailDto> purchaseDetailList = purchaseService.showDetailPurchase(id);
        return new ResponseEntity<>(purchaseDetailList, HttpStatus.OK);
    }
}
