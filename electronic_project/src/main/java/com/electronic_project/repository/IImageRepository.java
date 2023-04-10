package com.electronic_project.repository;

import com.electronic_project.model.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image where product_id = :id", nativeQuery = true)
    List<Image> findImageList(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into image(url, product_id) value (:url, :productId)", nativeQuery = true)
    void addImage(@Param("url") String url, @Param("productId") Integer productId);
}
