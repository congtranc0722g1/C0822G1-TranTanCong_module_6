package com.electronic_project.repository;

import com.electronic_project.dto.product.ICategoryListDto;
import com.electronic_project.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select category.id, category.name, category.description, category.avatar, count(product.id) as total from category\n" +
            "left join product on category.id = product.category_id\n" +
            "group by category.id", nativeQuery = true)
    List<ICategoryListDto> showAll();
}
