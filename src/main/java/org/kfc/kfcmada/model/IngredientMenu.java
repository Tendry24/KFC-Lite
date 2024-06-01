package org.kfc.kfcmada.model;

import lombok.Data;

@Data
public class IngredientMenu {
    private Long id;
    private Menu menu;
    private IngredientTempl ingredient;
    private Double quantity;
}
