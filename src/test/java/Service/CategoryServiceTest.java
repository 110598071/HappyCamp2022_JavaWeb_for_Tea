package Service;

import Entity.Category;
import junit.framework.TestCase;
import org.junit.Assert;
import java.util.List;

public class CategoryServiceTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testShow() {
        CategoryService categoryService = new CategoryService();
        List<Category> list = categoryService.show();

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("Travel", list.get(0).getName());
    }

    public void testInsertCategory() {
        CategoryService categoryService = new CategoryService();
        categoryService.insertCategory("DAOTest");

        List<Category> list = categoryService.show();
        Assert.assertEquals("DAOTest", list.get(list.size()-1).getName());
        categoryService.deleteCategoryById(list.get(list.size()-1).getId());
    }

    public void testDeleteCategoryById() {
        CategoryService categoryService = new CategoryService();
        categoryService.insertCategory("DAOTest");

        List<Category> list = categoryService.show();
        int deleteCategoryId = list.get(list.size()-1).getId();
        categoryService.deleteCategoryById(deleteCategoryId);

        list = categoryService.show();
        Assert.assertNotEquals(new Integer(deleteCategoryId), list.get(list.size()-1).getId());
    }
}