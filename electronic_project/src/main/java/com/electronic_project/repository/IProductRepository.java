package com.electronic_project.repository;

import com.electronic_project.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product order by create_day desc limit 0, 8", nativeQuery = true)
    List<Product> showLatestProductList();

}
