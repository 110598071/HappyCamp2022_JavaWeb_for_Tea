package Dao;

import Entity.User;
import junit.framework.TestCase;
import org.junit.Assert;
import java.util.List;

public class UserDAOTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSelectUserByAccountAndPassword() {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectUserByAccountAndPassword("123", "123");
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals(1, user.getRole());
        Assert.assertEquals("123", user.getAccount());
        Assert.assertEquals("123", user.getPassword());
    }

    public void testInsertUser() {
        UserDAO userDAO = new UserDAO();
        String account = "abc";
        String password = "def";
        userDAO.insertUser(2, account, password);

        User user = userDAO.selectUserByAccountAndPassword(account, password);
        Assert.assertEquals(2, user.getRole());
        Assert.assertEquals(account, user.getAccount());
        Assert.assertEquals(password, user.getPassword());

        userDAO.deleteUserById(user.getId());
    }

    public void testSelectAllUser() {
        UserDAO userDAO = new UserDAO();
        List<User> user = userDAO.selectAllUser();
        Assert.assertEquals(1, user.get(0).getId());
        Assert.assertEquals(1, user.get(0).getRole());
        Assert.assertEquals("123", user.get(0).getAccount());
        Assert.assertEquals("123", user.get(0).getPassword());
    }

    public void testDeleteUserById() {
        UserDAO userDAO = new UserDAO();
        String account = "abc";
        String password = "def";
        userDAO.insertUser(2, account, password);
        userDAO.deleteUserById(userDAO.selectUserByAccountAndPassword(account, password).getId());

        User user = userDAO.selectUserByAccountAndPassword(account, password);
        Assert.assertNull(user);
    }
}