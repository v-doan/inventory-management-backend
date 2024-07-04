package com.example.inventory_management.repository;

import com.example.inventory_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIngredients_Id(Long ingredientId);
}
