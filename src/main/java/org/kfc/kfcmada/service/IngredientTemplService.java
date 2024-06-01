package org.kfc.kfcmada.service;

import org.kfc.kfcmada.model.IngredientTempl;
import org.kfc.kfcmada.repository.IngredientTemplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientTemplService {
    @Autowired
    private final IngredientTemplRepository ingredientTemplRepository;

    public IngredientTemplService(IngredientTemplRepository ingredientTemplRepository) {
        this.ingredientTemplRepository = ingredientTemplRepository;
    }
    public List<IngredientTempl> findAllIngredient(){
        return ingredientTemplRepository.findAllIngredient();
    }
}
