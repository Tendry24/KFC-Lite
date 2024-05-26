package org.kfc.kfcmada.repository;

import org.kfc.kfcmada.dto.StockResult;
import org.kfc.kfcmada.model.MovementType;
import org.kfc.kfcmada.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepository {
    @Autowired
    private DataSource dataSource;

    public Stock makeMovement(Stock stock) {
        String sql = "insert into stock_movement (id_resto, id_ingredient, quantity, movement_type,unity_id) " +
                "values (?,?,?,?,?)";
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, stock.getIdResto());
            statement.setLong(2, stock.getIdIngredientTempl());
            if(stock.getMovementType().equals(MovementType.OUT)){
                statement.setDouble(3, -stock.getQuantity());
            }else{
                statement.setDouble(3, stock.getQuantity());
            }
            statement.setString(4, stock.getMovementType().toString());
            statement.setLong(5, stock.getUnityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    public List<Stock> getAllMovement(Long idResto, Long idIngredient)  {
        String sql = "select * from stock_movement where id_resto = ? and id_ingredient= ?";
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, idResto);
            statement.setLong(2, idIngredient);

            statement.executeQuery();

            ResultSet result = statement.getResultSet();
            List<Stock> list = new ArrayList<>();
            while(result.next()){
                Stock stock = new Stock();
                stock.setId(result.getLong("id"));
                stock.setIdResto(result.getLong("id_resto"));
                stock.setIdIngredientTempl(result.getLong("id_ingredient"));
                stock.setQuantity(result.getDouble("quantity"));
                stock.setMovementType(MovementType.valueOf(result.getString("movement_type")));
                stock.setUnityId(result.getLong("unity_id"));
                stock.setMovementDateTime(result.getTimestamp("movement_datetime").toInstant());
                list.add(stock);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StockResult> findMoveBetweenOnDate(String date){
        String sql = "SELECT id_ingredient,quantity FROM stock_movement WHERE movement_datetime <= ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            if (date == null){
                statement.setTimestamp(1,Timestamp.valueOf(LocalDateTime.now()));
            }else {
                statement.setTimestamp(1,Timestamp.valueOf(date));
            }

            statement.executeQuery();

            ResultSet resultSet = statement.getResultSet();
            List<StockResult> results = new ArrayList<>();
            while(resultSet.next()){
                StockResult stockResults = new StockResult();
                stockResults.setIngredientTempl(resultSet.getLong("id_ingredient"));
                stockResults.setQuantity(resultSet.getDouble("quantity"));
                results.add(stockResults);
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

