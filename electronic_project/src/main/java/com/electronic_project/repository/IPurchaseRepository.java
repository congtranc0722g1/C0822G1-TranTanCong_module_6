package com.electronic_project.repository;

import com.electronic_project.model.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAll();
}
