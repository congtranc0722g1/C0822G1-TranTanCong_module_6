package com.electronic_project.repository;

import com.electronic_project.model.purchase.Purchase;
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
    @Query(value = "update purchase set purchase_status_id = :purchaseStatus, order_date = :orderDate where id = :id", nativeQuery = true)
    void updatePurchase(@Param("purchaseStatus") Integer purchaseStatus, @Param("orderDate") String orderDate, @Param("id") Integer id);
}
