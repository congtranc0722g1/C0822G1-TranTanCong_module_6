package com.electronic_project.service.impl;

import com.electronic_project.model.product.Category;
import com.electronic_project.repository.ICategoryRepository;
import com.electronic_project.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> showAll() {
        return categoryRepository.showAll();
    }
}
