package org.kfc.kfcmada.model;

import lombok.*;

import java.time.Instant;

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
    private Instant movementDateTime;
    private MovementType movementType;
}
