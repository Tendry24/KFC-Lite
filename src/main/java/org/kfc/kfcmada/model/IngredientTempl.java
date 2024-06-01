package org.kfc.kfcmada.model;

import lombok.Data;
import lombok.Getter;

@Data
public class IngredientTempl {
    private Long id;
    private String name;
    private Double price;
    private Unity unité;
}
