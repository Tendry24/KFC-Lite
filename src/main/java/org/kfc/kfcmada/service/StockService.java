package org.kfc.kfcmada.service;

import org.kfc.kfcmada.dto.StockResult;
import org.kfc.kfcmada.model.Stock;
import org.kfc.kfcmada.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock save(Stock stock) {
        return stockRepository.makeMovement(stock);
    }

    public List<Stock> getAllMovement(Long idResto, Long idIngredient) {
        return stockRepository.getAllMovement(idResto, idIngredient);
    }

    public Double getStockByGivenDate(String date){
        List<StockResult> allMove=  stockRepository.findMoveBetweenOnDate(date);
        return allMove.stream().mapToDouble(StockResult::getQuantity).sum();
    }
}
