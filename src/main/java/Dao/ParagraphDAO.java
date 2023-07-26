package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DBConnection.DBConnection;
import Entity.Paragraph;
import Entity.Category;

public class ParagraphDAO {
    private final DBConnection dbConnection = new DBConnection();
    private static final String SELECT_ALL_PARAGRAPH = "SELECT paragraph_id, title, date, text, c.category_id, category_name FROM \"Paragraph\" AS p " +
                                                        "JOIN \"Category\" AS c ON p.category_id = c.category_id ORDER BY paragraph_id";
    private static final String SELECT_PARAGRAPH_BY_USER_ID = "SELECT paragraph_id, title, date, text, c.category_id, category_name FROM \"Paragraph\" AS p " +
                                             "JOIN \"Category\" AS c ON p.category_id = c.category_id WHERE p.user_id = ? ORDER BY paragraph_id";
    private static final String INSERT_PARAGRAPH = "INSERT INTO \"Paragraph\" (title, date, text, category_id, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_PARAGRAPH_BY_PARAGRAPH_ID = "DELETE FROM \"Paragraph\" WHERE paragraph_id = ?";
    private static final String DELETE_PARAGRAPH_BY_CATEGORY_ID = "DELETE FROM \"Paragraph\" WHERE category_id = ?";
    private static final String DELETE_PARAGRAPH_BY_USER_ID = "DELETE FROM \"Paragraph\" WHERE user_id = ?";
    private static final String SELECT_PARAGRAPH_BY_PARAGRAPH_ID_AND_USER_ID = "SELECT title, date, text, c.category_id, category_name FROM \"Paragraph\" AS p " +
                                                        "JOIN \"Category\" AS c ON p.category_id = c.category_id " +
                                                        "WHERE p.paragraph_id = ? AND p.user_id = ?";
    private static final String SELECT_PARAGRAPH_BY_CATEGORY_ID_AND_USER_ID = "SELECT paragraph_id, title, date, text, c.category_id, category_name FROM \"Paragraph\" AS p " +
                                                                "JOIN \"Category\" AS c ON p.category_id = c.category_id WHERE p.category_id = ? AND p.user_id = ? ORDER BY paragraph_id";
    private static final String UPDATE_PARAGRAPH_BY_ID = "UPDATE \"Paragraph\" SET title = ?, date = ?, text = ?, category_id = ? " +
                                                        "WHERE paragraph_id = ?";

    public List<Paragraph> selectAllParagraph() {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        List<Paragraph> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PARAGRAPH))
        {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Paragraph paragraph = new Paragraph();
                paragraph.setId(resultSet.getInt("paragraph_id"));
                paragraph.setTitle(resultSet.getString("title"));
                paragraph.setDate(resultSet.getString("date"));
                paragraph.setText(resultSet.getString("text"));

                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));

                paragraph.setCategory(category);
                list.add(paragraph);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Paragraph> selectParagraphByUserId(int userId) {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        List<Paragraph> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARAGRAPH_BY_USER_ID))
        {
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Paragraph paragraph = new Paragraph();
                paragraph.setId(resultSet.getInt("paragraph_id"));
                paragraph.setTitle(resultSet.getString("title"));
                paragraph.setDate(resultSet.getString("date"));
                paragraph.setText(resultSet.getString("text"));

                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));

                paragraph.setCategory(category);
                list.add(paragraph);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertParagraph(Paragraph paragraph, int category_id, int user_id) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PARAGRAPH))
        {
            preparedStatement.setString(1,paragraph.getTitle());
            preparedStatement.setString(2,paragraph.getDate());
            preparedStatement.setString(3,paragraph.getText());
            preparedStatement.setInt(4,category_id);
            preparedStatement.setInt(5,user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParagraphByParagraphId(int paragraphId) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PARAGRAPH_BY_PARAGRAPH_ID))
        {
            preparedStatement.setInt(1,paragraphId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParagraphByCategoryId(int categoryId) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PARAGRAPH_BY_CATEGORY_ID))
        {
            preparedStatement.setInt(1,categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParagraphByUserId(int userId) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PARAGRAPH_BY_USER_ID))
        {
            preparedStatement.setInt(1,userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Paragraph selectParagraphByParagraphIdAndUserId(int paragraphId, int userId) {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        Paragraph paragraph = new Paragraph();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARAGRAPH_BY_PARAGRAPH_ID_AND_USER_ID))
        {
            preparedStatement.setInt(1,paragraphId);
            preparedStatement.setInt(2,userId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            paragraph.setId(paragraphId);
            paragraph.setTitle(resultSet.getString("title"));
            paragraph.setDate(resultSet.getString("date"));
            paragraph.setText(resultSet.getString("text"));

            Category category = new Category();
            category.setId(resultSet.getInt("category_id"));
            category.setName(resultSet.getString("category_name"));

            paragraph.setCategory(category);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paragraph;
    }

    public List<Paragraph> selectParagraphByCategoryIdAndUserId(int categoryId, int userId) {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        List<Paragraph> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARAGRAPH_BY_CATEGORY_ID_AND_USER_ID))
        {
            preparedStatement.setInt(1,categoryId);
            preparedStatement.setInt(2,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Paragraph paragraph = new Paragraph();
                paragraph.setId(resultSet.getInt("paragraph_id"));
                paragraph.setTitle(resultSet.getString("title"));
                paragraph.setDate(resultSet.getString("date"));
                paragraph.setText(resultSet.getString("text"));

                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));

                paragraph.setCategory(category);
                list.add(paragraph);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(String title, String date, String text, int paragraphId, int CategoryId) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PARAGRAPH_BY_ID))
        {
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,date);
            preparedStatement.setString(3,text);
            preparedStatement.setInt(4,CategoryId);
            preparedStatement.setInt(5,paragraphId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
