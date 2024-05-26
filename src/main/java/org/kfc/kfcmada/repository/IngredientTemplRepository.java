package org.kfc.kfcmada.repository;

import org.kfc.kfcmada.model.IngredientTempl;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class IngredientTemplRepository {
    private DataSource datasource;

    public IngredientTempl makeMovement(IngredientTempl ingredientTempl) throws SQLException {
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
