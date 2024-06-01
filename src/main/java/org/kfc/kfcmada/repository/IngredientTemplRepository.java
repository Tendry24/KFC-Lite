package org.kfc.kfcmada.repository;

import lombok.AllArgsConstructor;
import org.kfc.kfcmada.model.IngredientTempl;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class IngredientTemplRepository {
    private final DataSource datasource;

    private final UnityRepository unityRepository;

    public List<IngredientTempl> findAllIngredient(){
        String sql = "SELECT * FROM ingredient_template";
        try {
            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);

            ResultSet result = statement.getResultSet();
            List<IngredientTempl> list = new ArrayList<>();
            while(result.next()){
                IngredientTempl ingredientTempl = new IngredientTempl();
                ingredientTempl.setId(result.getLong("id"));
                ingredientTempl.setName(result.getString("name"));
                ingredientTempl.setPrice(result.getDouble("price"));
                ingredientTempl.setUnité(unityRepository.findById(result.getLong("unity_id")));
                list.add(ingredientTempl);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public IngredientTempl saveIngredient(IngredientTempl ingredientTempl) throws SQLException {
        String sql = "INSERT INTO ingredient_template (name, price, unity_id) VALUES (?, ?, ?)";

        try {
            Connection connection = datasource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, ingredientTempl.getName());
            statement.setDouble(2, ingredientTempl.getPrice());
            statement.setLong(3, ingredientTempl.getUnité().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredientTempl;
    }
}
