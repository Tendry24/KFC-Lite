package org.kfc.kfcmada.repository;

import org.kfc.kfcmada.model.Unity;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UnityRepository {
    private final DataSource dataSource;

    public UnityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Unity findById(Long id){
        String sql = "SELECT * FROM unity WHERE id = ?";
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            Unity unity = new Unity();
            while(result.next()){
                unity.setId(result.getLong("id"));
                unity.setName(result.getString("name"));
            }
            return unity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
