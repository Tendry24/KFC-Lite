package org.kfc.kfcmada.controller;

import org.kfc.kfcmada.model.IngredientTempl;
import org.kfc.kfcmada.service.IngredientTemplService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ingredient")
public class IngredientTemplController {
    private final IngredientTemplService ingredientTemplService;

    public IngredientTemplController(IngredientTemplService ingredientTemplService) {
        this.ingredientTemplService = ingredientTemplService;
    }

    @GetMapping("/all")
    public List<IngredientTempl> getAllIngredient(){
        return ingredientTemplService.findAllIngredient();
    }
}
