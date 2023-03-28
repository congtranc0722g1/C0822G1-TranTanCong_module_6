package com.electronic_project.repository;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.purchase.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartRepository extends JpaRepository<PurchaseDetail, Integer> {

    @Query(value = "select image.url , product.name, product.price, purchase_detail.quantity, (ifnull(product.price, 0) * ifnull(purchase_detail.quantity, 0)) as total from purchase_detail join product on purchase_detail.product_id = product.id join image on product.id = image.product_id join purchase on purchase_detail.purchase_id = purchase.id join\n" +
            "user on purchase.user_id = user.id where user.id = :id group by product.id", nativeQuery = true)
    List<ICartDto> getCart(@Param("id") Integer id);
}
