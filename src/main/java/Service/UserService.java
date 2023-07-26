package Service;

import Entity.User;
import Dao.UserDAO;
import java.util.List;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public User selectUserByAccountAndPassword(String account, String password) {
        return userDAO.selectUserByAccountAndPassword(account, password);
    }

    public void insertUser(int role, String account, String password) {
        try {
            userDAO.insertUser(role, account, password);
        }
        catch(RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> selectAllUser() {
        return userDAO.selectAllUser();
    }

    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }

    public User selectUserByAccountAndPasswordForSQLInjection(String account, String password) {
        return userDAO.selectUserByAccountAndPasswordForSQLInjection(account, password);
    }
}
