package org.kfc.kfcmada.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MovementResult {
    private Timestamp movementDateTime;
    private String name;
    private String movementType;
    private Double quantity;
}
