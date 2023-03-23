package com.electronic_project.repository;

import com.electronic_project.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product order by create_day desc limit 0, 8", nativeQuery = true)
    List<Product> showLatestProductList();

    @Query(value = "select * from product where name like concat('%', :name, '%')", nativeQuery = true)
    Page<Product> showAll(String name, Pageable pageable);

    @Query(value = "select * from product where category_id = :categoryId and trademark_id = :trademarkId and name like concat('%', :name, '%')", nativeQuery = true)
    Page<Product> getAllProduct(@Param("categoryId") Integer categoryId, @Param("trademarkId") Integer trademarkId, @Param("name") String name, Pageable pageable);

    @Query(value = "select * from product where category_id = :categoryId and name like concat('%', :name, '%')", nativeQuery = true)
    Page<Product> getCategoryProduct(@Param("categoryId") Integer categoryId, @Param("name") String name, Pageable pageable);

    @Query(value = "select * from product where trademark_id = :trademarkId and name like concat('%', :name, '%')", nativeQuery = true)
    Page<Product> getTrademarkProduct(@Param("trademarkId") Integer trademarkId, @Param("name") String name, Pageable pageable);

    @Query(value = "select * from product where id = :id", nativeQuery = true)
    Product findProduct(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into product(code, name, create_day, description, price, entry_price, quantity, flag_delete, category_id, trademark_id) value (:code, :name, :createDay, :description, :price, :entryPrice, :quantity, :flagDelete, :categoryId, :trademarkId)", nativeQuery = true)
    void addProduct(@Param("code") String code, @Param("name") String name, @Param("createDay") String createDay, @Param("description") String description, @Param("price") Double price, @Param("entryPrice") Double entryPrice, @Param("quantity") Integer quantiry, @Param("flagDelete") Boolean flagDelete, @Param("categoryId") Integer categoryId, @Param("trademarkId") Integer trademarkId);
}
