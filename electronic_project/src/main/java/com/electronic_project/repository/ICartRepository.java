package com.electronic_project.repository;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.purchase.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICartRepository extends JpaRepository<PurchaseDetail, Integer> {

        @Query(value = "SELECT image.url,product.id, product.quantity as inventory, product.name, product.price, (SELECT SUM(quantity) FROM purchase_detail JOIN purchase ON purchase_detail.purchase_id = purchase.id JOIN user ON purchase.user_id = user.id JOIN purchase_status ON purchase.purchase_status_id = purchase_status.id WHERE purchase_detail.product_id = product.id AND user.id = :id AND purchase_status.id = 1) AS quantity\n" +
                ", (IFNULL(product.price, 0) * (SELECT SUM(quantity) FROM purchase_detail JOIN purchase ON purchase_detail.purchase_id = purchase.id JOIN user ON purchase.user_id = user.id JOIN purchase_status ON purchase.purchase_status_id = purchase_status.id WHERE purchase_detail.product_id = product.id AND user.id = :id AND purchase_status.id = 1)) AS total \n" +
                "FROM purchase_detail \n" +
                "JOIN product ON purchase_detail.product_id = product.id \n" +
                "JOIN image ON product.id = image.product_id \n" +
                "JOIN purchase ON purchase_detail.purchase_id = purchase.id \n" +
                "JOIN purchase_status ON purchase.purchase_status_id = purchase_status.id\n" +
                "JOIN user ON purchase.user_id = user.id \n" +
                "WHERE user.id = :id AND purchase_status.id = 1\n" +
                "GROUP BY product.id", nativeQuery = true)
    List<ICartDto> getCart(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "insert into purchase_detail(quantity, product_id, purchase_id) values (:quantity, :productId, :purchaseId)", nativeQuery = true)
    void addCart(@Param("quantity") Integer quantity, @Param("productId") Integer productId, @Param("purchaseId") Integer purchaseId);

    @Modifying
    @Transactional
    @Query(value = "update purchase_detail set quantity = :quantity where product_id = :productId and purchase_id = :purchaseId", nativeQuery = true)
    void updateCart(@Param("quantity") Integer quantity, @Param("productId") Integer productId, @Param("purchaseId") Integer purchaseId);

    @Query(value = "SELECT sum((IFNULL(product.price, 0) * IFNULL(purchase_detail.quantity, 0))) AS total \n" +
            "FROM purchase_detail \n" +
            "JOIN product ON purchase_detail.product_id = product.id \n" +
            "JOIN purchase ON purchase_detail.purchase_id = purchase.id \n" +
            "JOIN purchase_status ON purchase.purchase_status_id = purchase_status.id\n" +
            "JOIN user ON purchase.user_id = user.id \n" +
            "WHERE user.id = :id AND purchase_status.id = 1", nativeQuery = true)
    Double getTotalPayment(@Param("id") Integer id);

    @Query(value = "select * from purchase_detail where product_id = :productId and purchase_id = :purchaseId", nativeQuery = true)
    PurchaseDetail findPurchase(@Param("productId") Integer productId, @Param("purchaseId") Integer purchaseId);

    @Modifying
    @Transactional
    @Query(value = "delete from purchase_detail where product_id = :productId and purchase_id = :purchaseId", nativeQuery = true)
    void deletePurchaseDetail(@Param("productId") Integer productId, @Param("purchaseId") Integer purchaseId);

}
