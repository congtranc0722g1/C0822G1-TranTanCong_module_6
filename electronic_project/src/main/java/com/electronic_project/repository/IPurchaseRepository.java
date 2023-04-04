package com.electronic_project.repository;

import com.electronic_project.dto.purchase.IPurchaseHistoryDto;
import com.electronic_project.model.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAll();

    @Modifying
    @Transactional
    @Query(value = "insert into purchase(`code`, purchase_status_id, user_id) value (:code, :purchaseStatusId, :userId)", nativeQuery = true)
    void addPurchase(@Param("code") String code, @Param("purchaseStatusId") Integer purchaseStatusId, @Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(value = "update purchase set purchase_status_id = :purchaseStatus, order_date = :orderDate, customer_name = :customerName, customer_phone = :customerPhone, customer_address = :customerAddress where id = :id", nativeQuery = true)
    void updatePurchase(@Param("purchaseStatus") Integer purchaseStatus, @Param("orderDate") String orderDate, @Param("customerName") String customerName, @Param("customerPhone") String customerPhone, @Param("customerAddress") String customerAddress, @Param("id") Integer id);

//    @Query(value = "SELECT purchase.id, purchase.`code`, purchase.order_date as orderDate, purchase_status.`name` as `status`, sum((IFNULL(product.price, 0) * IFNULL(purchase_detail.quantity, 0))) AS total\n" +
//            "FROM purchase\n" +
//            "JOIN purchase_detail ON purchase.id = purchase_detail.purchase_id\n" +
//            "JOIN product ON purchase_detail.product_id = product.id\n" +
//            "JOIN purchase_status ON purchase.purchase_status_id = purchase_status.id\n" +
//            "JOIN user ON purchase.user_id = `user`.id\n" +
//            "WHERE `user`.id = :id AND purchase_status.id != 1 group by purchase_detail.purchase_id", nativeQuery = true)
//    Page<IPurchaseHistoryDto> showAll(@Param("id") Integer id, Pageable pageable);
}
