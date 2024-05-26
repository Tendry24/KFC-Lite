package org.kfc.kfcmada.service;

import org.kfc.kfcmada.model.Stock;
import org.kfc.kfcmada.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock save(Stock stock) throws SQLException {
        return stockRepository.makeMovement(stock);
    }

    public List<Stock> getAllStock(Long idResto, Long idIngredient) throws SQLException {
        return stockRepository.getAllStock(idResto, idIngredient);
    }
}
