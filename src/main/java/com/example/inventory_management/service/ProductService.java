package com.example.inventory_management.service;

import com.example.inventory_management.model.Ingredient;
import com.example.inventory_management.model.Product;
import com.example.inventory_management.repository.IngredientRepository;
import com.example.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Ingredient addIngredientToProduct(Long productId, String ingredientName) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Ingredient ingredient = ingredientRepository.findByNameIgnoreCase(ingredientName)
                .orElseGet(() -> {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.setName(ingredientName);
                    return ingredientRepository.save(newIngredient);
                });

        product.getIngredients().add(ingredient);
        productRepository.save(product); // Save the product to ensure the association is updated

        return ingredient;
    }

    @Transactional
    public void removeIngredientFromProduct(Long productId, Long ingredientId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        product.getIngredients().remove(ingredient);
        productRepository.save(product);
    }

    public Set<Ingredient> getIngredientsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return product.getIngredients();
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByIngredient(Long ingredientId) {
        return productRepository.findByIngredients_Id(ingredientId);
    }
}
