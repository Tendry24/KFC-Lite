package org.kfc.kfcmada.controller;

import org.kfc.kfcmada.model.Stock;
import org.kfc.kfcmada.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("")
    public Stock saveMovement(@RequestBody Stock stock){
        return stockService.save(stock);
    }

    @GetMapping("")
    public List<Stock> findAllMovement(@RequestParam(required = false) Long idResto) {
        return stockService.getAllMovement(idResto);
    }

    @GetMapping("/on-date")
    public Double findStockOnDate(@RequestParam(required = false) String date,
                                  @RequestParam Long idResto,
                                  @RequestParam Long idIngredient
    ){
        return stockService.getStockByGivenDate(date,idResto,idIngredient);
    }
 }
