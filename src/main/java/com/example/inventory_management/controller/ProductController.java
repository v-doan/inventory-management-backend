package com.example.inventory_management.controller;

import com.example.inventory_management.dto.IngredientRequest;
import com.example.inventory_management.model.Ingredient;
import com.example.inventory_management.model.Product;
import com.example.inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows all origins
public class ProductController {

    @Autowired
    private ProductService productService;

    //Endpoint to retrieve all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Endpoint to retrieve all ingredients for a specific product
    @GetMapping("/products/{productId}/ingredients")
    public ResponseEntity<Set<Ingredient>> getAllIngredientsByProductId(@PathVariable Long productId) {
        Set<Ingredient> ingredients = productService.getIngredientsByProductId(productId);
        return ResponseEntity.ok(ingredients);
    }

    // Endpoint to retrieve all products that use a specific ingredient
    @GetMapping("/ingredients/{ingredientId}/products")
    public ResponseEntity<List<Product>> getProductsByIngredientId(@PathVariable Long ingredientId) {
        List<Product> products = productService.getProductsByIngredient(ingredientId);
        return ResponseEntity.ok(products);
    }

    // Add a new ingredient to a product
    @PostMapping("/products/{productId}/ingredients")
    public ResponseEntity<Ingredient> addIngredientToProduct(
            @PathVariable Long productId,
            @RequestBody IngredientRequest ingredientRequest) {
        Ingredient newIngredient = productService.addIngredientToProduct(productId, ingredientRequest.getName());
        return ResponseEntity.ok(newIngredient);
    }

    // Remove an ingredient from a product
    @DeleteMapping("/products/{productId}/ingredients/{ingredientId}")
    public ResponseEntity<Void> removeIngredientFromProduct(
            @PathVariable Long productId,
            @PathVariable Long ingredientId) {
        productService.removeIngredientFromProduct(productId, ingredientId);
        return ResponseEntity.noContent().build();
    }
}
