package com.electronic_project.controller;

import com.electronic_project.dto.product.ICategoryListDto;
import com.electronic_project.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    private ResponseEntity<List<ICategoryListDto>> showAll(){
        List<ICategoryListDto> categoryList = categoryService.showAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
