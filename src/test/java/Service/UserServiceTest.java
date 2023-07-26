package Service;

import Entity.User;
import junit.framework.TestCase;
import org.junit.Assert;
import java.util.List;

public class UserServiceTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSelectUserByAccountAndPassword() {
        UserService userService = new UserService();
        User user = userService.selectUserByAccountAndPassword("123", "123");
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals(1, user.getRole());
        Assert.assertEquals("123", user.getAccount());
        Assert.assertEquals("123", user.getPassword());
    }

    public void testInsertUser() {
        UserService userService = new UserService();
        String account = "abc";
        String password = "def";
        userService.insertUser(2, account, password);

        User user = userService.selectUserByAccountAndPassword(account, password);
        Assert.assertEquals(2, user.getRole());
        Assert.assertEquals(account, user.getAccount());
        Assert.assertEquals(password, user.getPassword());

        userService.deleteUserById(user.getId());
    }

    public void testSelectAllUser() {
        UserService userService = new UserService();
        List<User> user = userService.selectAllUser();
        Assert.assertEquals(1, user.get(0).getId());
        Assert.assertEquals(1, user.get(0).getRole());
        Assert.assertEquals("123", user.get(0).getAccount());
        Assert.assertEquals("123", user.get(0).getPassword());
    }

    public void testDeleteUserById() {
        UserService userService = new UserService();
        String account = "abc";
        String password = "def";
        userService.insertUser(2, account, password);
        userService.deleteUserById(userService.selectUserByAccountAndPassword(account, password).getId());

        User user = userService.selectUserByAccountAndPassword(account, password);
        Assert.assertNull(user);
    }
}