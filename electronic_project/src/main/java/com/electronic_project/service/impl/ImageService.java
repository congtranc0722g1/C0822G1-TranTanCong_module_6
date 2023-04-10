package com.electronic_project.service.impl;

import com.electronic_project.model.product.Image;
import com.electronic_project.repository.IImageRepository;
import com.electronic_project.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Override
    public List<Image> findImageList(Integer id) {
        return imageRepository.findImageList(id);
    }

    @Override
    public void addImage(String url, Integer productId) {
        imageRepository.addImage(url, productId);
    }
}
