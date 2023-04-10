package com.electronic_project.repository;

import com.electronic_project.dto.product.IRevenueProductDto;
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

    @Query(value = "select * from product where flag_delete = true order by create_day desc limit 0, 8", nativeQuery = true)
    List<Product> showLatestProductList();

    @Query(value = "select * from product where name like concat('%', :name, '%') and flag_delete = true", nativeQuery = true)
    Page<Product> showAll(String name, Pageable pageable);

    @Query(value = "select * from product where category_id = :categoryId and trademark_id = :trademarkId and name like concat('%', :name, '%') and flag_delete = true", nativeQuery = true)
    Page<Product> getAllProduct(@Param("categoryId") Integer categoryId, @Param("trademarkId") Integer trademarkId, @Param("name") String name, Pageable pageable);

    @Query(value = "select * from product where category_id = :categoryId and name like concat('%', :name, '%') and flag_delete = true", nativeQuery = true)
    Page<Product> getCategoryProduct(@Param("categoryId") Integer categoryId, @Param("name") String name, Pageable pageable);

    @Query(value = "select * from product where trademark_id = :trademarkId and name like concat('%', :name, '%') and flag_delete = true", nativeQuery = true)
    Page<Product> getTrademarkProduct(@Param("trademarkId") Integer trademarkId, @Param("name") String name, Pageable pageable);

    @Query(value = "select * from product where id = :id and flag_delete = true", nativeQuery = true)
    Product findProduct(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into product(code, name, create_day, description, price, entry_price, quantity, flag_delete, category_id, trademark_id) value (:code, :name, :createDay, :description, :price, :entryPrice, :quantity, :flagDelete, :categoryId, :trademarkId)", nativeQuery = true)
    void addProduct(@Param("code") String code, @Param("name") String name, @Param("createDay") String createDay, @Param("description") String description, @Param("price") Double price, @Param("entryPrice") Double entryPrice, @Param("quantity") Integer quantiry, @Param("flagDelete") Boolean flagDelete, @Param("categoryId") Integer categoryId, @Param("trademarkId") Integer trademarkId);

    @Query(value = "select count(*) from product where flag_delete = true", nativeQuery = true)
    Integer countProduct();

    @Query(value = "select * from product where flag_delete = true", nativeQuery = true)
    List<Product> showAll();

    @Modifying
    @Transactional
    @Query(value = "update product set quantity = :quantity where id = :id and flag_delete = true", nativeQuery = true)
    void updateQuantityProduct(@Param("quantity") Integer quantity, @Param("id") Integer id);

    @Query(value = "select * from product where category_id = :categoryId and flag_delete = true limit 0, 8", nativeQuery = true)
    List<Product> findProductByCategory(@Param("categoryId") Integer categoryId);

    @Query(value = "select image.url as image,product.id, product.quantity as inventory, product.name, product.price, (select SUM(quantity) from purchase_detail join purchase on purchase_detail.purchase_id = purchase.id join purchase_status on purchase.purchase_status_id = purchase_status.id where purchase_detail.product_id = product.id and purchase_status.id != 1) as quantity\n" +
            "                from purchase_detail\n" +
            "                join product on purchase_detail.product_id = product.id\n" +
            "                join image on product.id = image.product_id\n" +
            "                join purchase on purchase_detail.purchase_id = purchase.id\n" +
            "                join purchase_status on purchase.purchase_status_id = purchase_status.id\n" +
            "                where purchase_status.id != 1 and product.flag_delete = true\n" +
            "                group by product.id\n" +
            "                order by quantity desc, price desc\n" +
            "                LIMIT 0,8", nativeQuery = true)
    List<IRevenueProductDto> showRevenueProduct();


    @Modifying
    @Transactional
    @Query(value = "update product set name = :name, description = :description, price = :price, entry_price = :entryPrice, quantity = :quantity, category_id = :categoryId, trademark_id = :trademarkId where id = :id", nativeQuery = true)
    void updateProduct(@Param("name") String name, @Param("description") String description, @Param("price") Double price, @Param("entryPrice") Double entryPrice, @Param("quantity") Integer quantity, @Param("categoryId") Integer categoryId, @Param("trademarkId") Integer trademarkId, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update product set flag_delete = false where id = :id", nativeQuery = true)
    void deleteProduct(@Param("id") Integer id);
}
