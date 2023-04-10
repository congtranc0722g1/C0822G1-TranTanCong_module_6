package com.electronic_project.service;

import com.electronic_project.dto.product.IRevenueProductDto;
import com.electronic_project.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> showLatestProductList();

    Page<Product> showAll(String name, Pageable pageable);

    Page<Product> getAllProduct(Integer categoryId, Integer trademarkId, String name, Pageable pageable);

    Page<Product> getCategoryProduct(Integer categoryId, String name, Pageable pageable);

    Page<Product> getTrademarkProduct(Integer trademarkId, String name, Pageable pageable);

    Product findProduct(Integer id);

    void addProduct(String code, String name, String createDay, String description, Double price, Double entryPrice, Integer quantiry, Boolean flagDelete, Integer categoryId, Integer trademarkId);

    Integer countProduct();

    List<Product> showAll();

    void updateQuantityProduct(Integer quantity, Integer id);

    List<Product> findProductByCategory(Integer categoryId);

    List<IRevenueProductDto> showRevenueProduct();

    void updateProduct(String name, String description, Double price, Double entryPrice, Integer quantity, Integer categoryId, Integer trademarkId, Integer id);

    void deleteProduct(Integer id);
}
