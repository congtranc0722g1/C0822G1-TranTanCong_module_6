package com.electronic_project.controller;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.service.ICartService;
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
    private ResponseEntity<List<ICartDto>> getCart(@RequestParam(value = "id") Integer id){
        List<ICartDto> cartList = cartService.getCart(id);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }
}
