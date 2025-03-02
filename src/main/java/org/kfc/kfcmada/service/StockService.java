package org.kfc.kfcmada.service;

import org.kfc.kfcmada.dto.MovementResult;
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

    public List<MovementResult> getAllMovement(Long idResto) {
        return stockRepository.getAllMovement(idResto);
    }

    public Double getStockByGivenDate(String date,Long idResto,Long idIngredient){
        List<StockResult> allMove=  stockRepository.findMoveBetweenOnDate(date,idResto,idIngredient);
        return allMove.stream().mapToDouble(StockResult::getQuantity).sum();
    }
}
