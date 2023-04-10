package com.electronic_project.repository;

import com.electronic_project.dto.purchase.IPurchaseDetailDto;
import com.electronic_project.dto.purchase.IPurchaseHistoryDto;
import com.electronic_project.model.purchase.Purchase;
import com.electronic_project.model.purchase.PurchaseDetail;
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

    @Query(value = "SELECT p.id, p.code, p.order_date as orderDate, p.customer_name as customerName, p.customer_phone as customerPhone, p.customer_address as customerAddress, ps.name as status, sum((IFNULL(pr.price, 0) * IFNULL(pd.quantity, 0)))  " +
            "    AS total " +
            "            FROM `purchase` p " +
            "            JOIN `purchase_detail` pd ON p.id = pd.purchase_id " +
            "            JOIN `product` pr ON pd.product_id = pr.id " +
            "            JOIN `purchase_status` ps ON p.purchase_status_id = ps.id " +
            "            JOIN `user` u ON p.user_id = u.id " +
            "            WHERE u.id = :id AND ps.id != 1 GROUP BY p.id ORDER BY p.order_date DESC", nativeQuery = true)
    Page<IPurchaseHistoryDto> showAll(@Param("id") Integer id, Pageable pageable);

    @Query(value = "SELECT product.name, product.price, SUM(purchase_detail.quantity) AS totalQuantity " +
            ", (SUM(purchase_detail.quantity) * product.price) AS total " +
            "FROM purchase_detail " +
            "JOIN product ON purchase_detail.product_id = product.id  " +
            "JOIN purchase ON purchase_detail.purchase_id = purchase.id " +
            "JOIN purchase_status ON purchase.purchase_status_id = purchase_status.id " +
            "WHERE purchase.id= :id AND purchase_status.id != 1 " +
            "GROUP BY product.id", nativeQuery = true)
    List<IPurchaseDetailDto> showDetailPurchase(@Param("id") Integer id);
}
