package com.example.inventory_management.controller;

import com.example.inventory_management.model.Ingredient;
import com.example.inventory_management.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.inventory_management.model.Ingredient;
import java.util.*;
@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = "http://localhost:3000")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

}
