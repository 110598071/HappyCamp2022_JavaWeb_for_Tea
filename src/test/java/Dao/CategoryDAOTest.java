package Dao;

import Entity.Category;
import junit.framework.TestCase;
import org.junit.Assert;
import java.util.List;

public class CategoryDAOTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testShow() {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> list = categoryDAO.show();

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("Travel", list.get(0).getName());
    }

    public void testInsertCategory() {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insertCategory("DAOTest");

        List<Category> list = categoryDAO.show();
        Assert.assertEquals("DAOTest", list.get(list.size()-1).getName());
        categoryDAO.deleteCategoryById(list.get(list.size()-1).getId());
    }

    public void testDeleteCategoryById() {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insertCategory("DAOTest");

        List<Category> list = categoryDAO.show();
        int deleteCategoryId = list.get(list.size()-1).getId();
        categoryDAO.deleteCategoryById(deleteCategoryId);

        list = categoryDAO.show();
        Assert.assertNotEquals(new Integer(deleteCategoryId), list.get(list.size()-1).getId());
    }
}