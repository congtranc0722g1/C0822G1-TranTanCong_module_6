package com.electronic_project.controller;

import com.electronic_project.model.product.Product;
import com.electronic_project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/latest-product")
    private ResponseEntity<List<Product>> showLatestProductList(){
        List<Product> latestProductList = productService.showLatestProductList();
        return new ResponseEntity<>(latestProductList, HttpStatus.OK);
    }
}
