package com.electronic_project.service.impl;

import com.electronic_project.dto.product.IRevenueProductDto;
import com.electronic_project.model.product.Product;
import com.electronic_project.repository.IProductRepository;
import com.electronic_project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Product> showAll(String name, Pageable pageable) {
        return productRepository.showAll(name, pageable);
    }

    @Override
    public Page<Product> getAllProduct(Integer categoryId, Integer trademarkId, String name, Pageable pageable) {
        return productRepository.getAllProduct(categoryId, trademarkId, name, pageable);
    }

    @Override
    public Page<Product> getCategoryProduct(Integer categoryId, String name, Pageable pageable) {
        return productRepository.getCategoryProduct(categoryId, name, pageable);
    }

    @Override
    public Page<Product> getTrademarkProduct(Integer trademarkId, String name, Pageable pageable) {
        return productRepository.getTrademarkProduct(trademarkId, name, pageable);
    }

    @Override
    public Product findProduct(Integer id) {
        return productRepository.findProduct(id);
    }

    @Override
    public void addProduct(String code, String name, String createDay, String description, Double price, Double entryPrice, Integer quantiry, Boolean flagDelete, Integer categoryId, Integer trademarkId) {
        productRepository.addProduct(code, name, createDay, description, price, entryPrice, quantiry, flagDelete, categoryId, trademarkId);
    }

    @Override
    public Integer countProduct() {
        return productRepository.countProduct();
    }

    @Override
    public List<Product> showAll() {
        return productRepository.showAll();
    }

    @Override
    public void updateQuantityProduct(Integer quantity, Integer id) {
        productRepository.updateQuantityProduct(quantity, id);
    }

    @Override
    public List<Product> findProductByCategory(Integer categoryId) {
        return productRepository.findProductByCategory(categoryId);
    }

    @Override
    public List<IRevenueProductDto> showRevenueProduct() {
        return productRepository.showRevenueProduct();
    }

    @Override
    public void updateProduct(String name, String description, Double price, Double entryPrice, Integer quantity, Integer categoryId, Integer trademarkId, Integer id) {
        productRepository.updateProduct(name, description, price, entryPrice, quantity, categoryId, trademarkId, id);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }
}
