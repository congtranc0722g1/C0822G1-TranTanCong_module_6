package com.electronic_project.controller;

import com.electronic_project.dto.image.AddImageDto;
import com.electronic_project.model.product.Image;
import com.electronic_project.service.IImageService;
import com.electronic_project.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @GetMapping("list")
    private ResponseEntity<List<Image>> findImageList(@RequestParam(value = "id") Integer id){
        List<Image> imageList = imageService.findImageList(id);
        return new ResponseEntity<>(imageList, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<?> addImage(@RequestBody AddImageDto addImageDto){
        imageService.addImage(addImageDto.getUrl(), addImageDto.getProductId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
