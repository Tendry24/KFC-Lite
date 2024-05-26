package org.kfc.kfcmada.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Menu {
    private Long id;
    private String name;
    private List<IngredientTempl> ingredients;
    private Double price;
}
