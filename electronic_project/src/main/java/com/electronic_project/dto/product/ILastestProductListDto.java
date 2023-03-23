package com.electronic_project.dto.product;

import com.electronic_project.model.product.Image;

import java.util.List;

public interface ILastestProductListDto {
    Integer getId();
    String getName();
    Double getPrice();
    List<Image> getImages();
}
