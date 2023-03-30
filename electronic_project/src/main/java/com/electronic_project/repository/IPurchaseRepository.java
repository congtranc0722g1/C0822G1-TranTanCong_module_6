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
    @Query(value = "insert into purchase(`code`, order_date, purchase_status_id, user_id) value (:code, :orderDate, :purchaseStatusId, :userId)", nativeQuery = true)
    void addPurchase(@Param("code") String code, @Param("orderDate") String orderDate, @Param("purchaseStatusId") Integer purchaseStatusId, @Param("userId") Integer userId);
}
