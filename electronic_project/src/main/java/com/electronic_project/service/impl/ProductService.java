package com.electronic_project.service.impl;

import com.electronic_project.model.product.Product;
import com.electronic_project.repository.IProductRepository;
import com.electronic_project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> showLatestProductList() {
        return productRepository.showLatestProductList();
    }
}
