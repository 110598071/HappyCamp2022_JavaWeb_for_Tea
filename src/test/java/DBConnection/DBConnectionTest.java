package DBConnection;

import junit.framework.TestCase;
import org.junit.Assert;
import java.sql.Connection;

public class DBConnectionTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetConnection() {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        Assert.assertNotNull(conn);
    }
}