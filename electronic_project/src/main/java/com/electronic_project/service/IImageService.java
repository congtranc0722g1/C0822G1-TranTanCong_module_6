package com.electronic_project.service;

import com.electronic_project.model.product.Image;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageService {
    List<Image> findImageList(Integer id);

    void addImage(String url, Integer productId);
}
