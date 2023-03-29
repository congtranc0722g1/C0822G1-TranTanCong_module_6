package com.electronic_project.controller;

import com.electronic_project.dto.cart.AddCartDto;
import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.service.ICartService;
import com.electronic_project.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/list")
    private ResponseEntity<List<ICartDto>> getCart(@RequestParam(value = "id") Integer id) {
        List<ICartDto> cartList = cartService.getCart(id);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<?> addCart(@RequestBody AddCartDto addCartDto) {
        Integer purchaseId = cartService.checkPurchase(addCartDto.getUserId());
        if (purchaseId != -1) {
            cartService.addCart(addCartDto.getQuantity(), addCartDto.getProductId(), purchaseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/total-payment")
    private ResponseEntity<Double> getTotalPayment(@RequestParam(value = "id") Integer id) {
        Double totalPayment = cartService.getTotalPayment(id);
        return new ResponseEntity<>(totalPayment, HttpStatus.OK);
    }

}
