package org.kfc.kfcmada.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Stock {
    private Long id;
    private Long idResto;
    private Long idIngredientTempl;
    private Double quantity;
    private MovementType movementType;
    private Long unityId;
}
