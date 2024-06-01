package org.kfc.kfcmada.repository;

import org.kfc.kfcmada.dto.MovementResult;
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
        String sql = "insert into stock_movement (id_resto, id_ingredient, quantity, movement_type) " +
                "values (?,?,?,?)";
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
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    public List<MovementResult> getAllMovement(Long idResto)  {
        String sql = "select s.movement_datetime, i.name , s.quantity, s.movement_type from stock_movement s " +
                "INNER JOIN ingredient_template i on s.id_ingredient = i.id " +
                "where id_resto = ?";
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, idResto);

            statement.executeQuery();

            ResultSet result = statement.getResultSet();
            List<MovementResult> list = new ArrayList<>();
            while(result.next()){
                MovementResult movementResult = new MovementResult();
                movementResult.setMovementDateTime(result.getTimestamp("movement_datetime"));
                movementResult.setName(result.getString("name"));
                movementResult.setQuantity(result.getDouble("quantity"));
                movementResult.setMovementType(result.getString("movement_type"));
                list.add(movementResult);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StockResult> findMoveBetweenOnDate(String date,Long idResto,Long idIngredient){
        String sql = "SELECT id_ingredient,quantity FROM stock_movement WHERE movement_datetime <= ? and id_resto = ? and id_ingredient = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            if (date == null){
                statement.setTimestamp(1,Timestamp.valueOf(LocalDateTime.now()));
            }else {
                statement.setTimestamp(1,Timestamp.valueOf(date));
            }
            statement.setLong(2,idResto);
            statement.setLong(3,idIngredient);

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

