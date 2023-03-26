package com.electronic_project.repository;

import com.electronic_project.dto.product.ITrademarkListDto;
import com.electronic_project.model.product.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITrademarkRepository extends JpaRepository<Trademark, Integer> {

    @Query(value = "select trademark.id, trademark.name, trademark.description, count(product.id) as total from trademark\n" +
            "left join product on trademark.id = product.trademark_id\n" +
            "group by trademark.id;", nativeQuery = true)
    List<ITrademarkListDto> showAll();
}
