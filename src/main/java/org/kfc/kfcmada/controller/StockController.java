package org.kfc.kfcmada.controller;

import org.kfc.kfcmada.model.Stock;
import org.kfc.kfcmada.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("")
    public Stock saveStock(@RequestBody Stock stock) throws SQLException {
        return stockService.save(stock);
    }

    @GetMapping("")
    public List<Stock> findAllStock(@RequestParam Long idResto, @RequestParam Long idIngredient) throws SQLException {
        return stockService.getAllStock(idResto, idIngredient);
    }
 }
