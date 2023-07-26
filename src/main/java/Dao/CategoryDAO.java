package Dao;

import Entity.Category;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final DBConnection dbConnection = new DBConnection();
    private static final String SELECT_ALL_CATEGORY = "SELECT category_id, category_name FROM \"Category\" ORDER BY category_id";
    private static final String INSERT_CATEGORY = "INSERT INTO \"Category\" (category_name) VALUES (?)";
    private static final String DELETE_CATEGORY_BY_ID = "DELETE FROM \"Category\" WHERE category_id = ?";

    public List<Category> show() {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        List<Category> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY))
        {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));

                list.add(category);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertCategory(String categoryName) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY))
        {
            preparedStatement.setString(1,categoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoryById(int categoryId) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID))
        {
            preparedStatement.setInt(1,categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
