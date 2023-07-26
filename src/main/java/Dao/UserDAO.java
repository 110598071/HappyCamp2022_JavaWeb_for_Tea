package Dao;

import Entity.User;
import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final DBConnection dbConnection = new DBConnection();
    private static final String SELECT_USER_BY_ACCOUNT_AND_PASSWORD = "SELECT id, role, account, password FROM \"user\" WHERE account = ? AND password = ?";
    private static final String SELECT_ALL_USER = "SELECT id, role, account, password FROM \"user\" ORDER BY role, id";
    private static final String INSERT_USER = "INSERT INTO \"user\" (role, account, password) VALUES (?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM \"user\" WHERE id = ?";

    public User selectUserByAccountAndPassword(String account, String password) {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ACCOUNT_AND_PASSWORD))
        {
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getInt("role"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
            }
            else {
                user = null;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insertUser(int role, String account, String password) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER))
        {
            preparedStatement.setInt(1,role);
            preparedStatement.setString(2,account);
            preparedStatement.setString(3,password);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<User> selectAllUser() {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet;
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER))
        {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getInt("role"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteUserById(int id) {
        Connection connection = dbConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID))
        {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUserByAccountAndPasswordForSQLInjection(String account, String password) {
        Connection connection = dbConnection.getConnection();
        User user = new User();
        try (Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT id, role, account, password FROM \"user\" WHERE account = '"+account+"' AND password = '"+password+"'");
            if(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getInt("role"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
            }
            else {
                user = null;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
