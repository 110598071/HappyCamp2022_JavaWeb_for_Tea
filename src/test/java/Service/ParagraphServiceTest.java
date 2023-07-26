package Service;

import Entity.Category;
import Entity.Paragraph;
import Entity.User;
import junit.framework.TestCase;
import org.junit.Assert;
import java.util.List;

public class ParagraphServiceTest extends TestCase {
    private final int userId = 1;

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSelectAllParagraph() {
        ParagraphService paragraphService = new ParagraphService();
        List<Paragraph> list = paragraphService.selectAllParagraph();

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("HappyCamp", list.get(0).getTitle());
        Assert.assertEquals("2022-07-12", list.get(0).getDate());
        Assert.assertEquals("HAPPY!!!", list.get(0).getText());
        Assert.assertEquals(new Integer(7), list.get(0).getCategory().getId());
        Assert.assertEquals("For DAO Test", list.get(0).getCategory().getName());
    }

    public void testSelectParagraphByUserId() {
        ParagraphService paragraphService = new ParagraphService();
        List<Paragraph> list = paragraphService.selectParagraphByUserId(userId);

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("HappyCamp", list.get(0).getTitle());
        Assert.assertEquals("2022-07-12", list.get(0).getDate());
        Assert.assertEquals("HAPPY!!!", list.get(0).getText());
        Assert.assertEquals(new Integer(7), list.get(0).getCategory().getId());
        Assert.assertEquals("For DAO Test", list.get(0).getCategory().getName());
    }

    public void testInsertParagraph() {
        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DAOTest");
        paragraph.setDate("2022-02-28");
        paragraph.setText("DAOTestText");

        ParagraphService paragraphService = new ParagraphService();
        paragraphService.insertParagraph(paragraph, 3, userId);

        List<Paragraph> list = paragraphService.selectParagraphByUserId(1);
        Assert.assertEquals("DAOTest", list.get(list.size()-1).getTitle());
        Assert.assertEquals("2022-02-28", list.get(list.size()-1).getDate().toString());
        Assert.assertEquals("DAOTestText", list.get(list.size()-1).getText());
        Assert.assertEquals(new Integer(3), list.get(list.size()-1).getCategory().getId());

        paragraphService.deleteParagraphByParagraphId(list.get(list.size()-1).getId());
    }

    public void testDeleteParagraphByParagraphId() {
        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DeleteTest");
        paragraph.setDate("2010-03-27");
        paragraph.setText("DeleteTestText");

        ParagraphService paragraphService = new ParagraphService();
        paragraphService.insertParagraph(paragraph, 5, userId);
        List<Paragraph> list = paragraphService.selectParagraphByUserId(1);
        int deleteParagraphId = list.get(list.size()-1).getId();

        paragraphService.deleteParagraphByParagraphId(deleteParagraphId);
        list = paragraphService.selectParagraphByUserId(userId);
        Assert.assertNotEquals(new Integer(deleteParagraphId), list.get(list.size()-1).getId());
    }

    public void testDeleteParagraphByCategoryId() {
        CategoryService categoryService = new CategoryService();
        categoryService.insertCategory("DAOTest");
        List<Category> categoryList = categoryService.show();
        int categoryId = categoryList.get(categoryList.size()-1).getId();

        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DeleteTestByCategoryId");
        paragraph.setDate("2010-03-27");
        paragraph.setText("DeleteTestText");
        ParagraphService paragraphService = new ParagraphService();
        paragraphService.insertParagraph(paragraph, categoryId, userId);

        paragraphService.deleteParagraphByCategoryId(categoryId);
        List<Paragraph> paragraphList = paragraphService.selectParagraphByCategoryIdAndUserId(categoryId, userId);
        Assert.assertEquals(0, paragraphList.size());

        categoryService.deleteCategoryById(categoryId);
    }

    public void testDeleteParagraphByUserId() {
        int categoryId = 7;

        UserService userService = new UserService();
        userService.insertUser(2, "testAccount", "testPassword");
        List<User> userList = userService.selectAllUser();
        int userId = userList.get(userList.size()-1).getId();

        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DeleteTestByUserId");
        paragraph.setDate("2010-03-27");
        paragraph.setText("DeleteTestText");
        ParagraphService paragraphService = new ParagraphService();
        paragraphService.insertParagraph(paragraph, categoryId, userId);

        paragraphService.deleteParagraphByUserId(userId);
        List<Paragraph> paragraphList = paragraphService.selectParagraphByCategoryIdAndUserId(categoryId, userId);
        Assert.assertEquals(0, paragraphList.size());

        userService.deleteUserById(userId);
    }

    public void testSelectParagraphByParagraphIdAndUserId() {
        ParagraphService paragraphService = new ParagraphService();
        Paragraph paragraph = paragraphService.selectParagraphByParagraphIdAndUserId(1, userId);

        Assert.assertEquals(new Integer(1), paragraph.getId());
        Assert.assertEquals("HappyCamp", paragraph.getTitle());
        Assert.assertEquals("2022-07-12", paragraph.getDate());
        Assert.assertEquals("HAPPY!!!", paragraph.getText());
        Assert.assertEquals(new Integer(7), paragraph.getCategory().getId());
        Assert.assertEquals("For DAO Test", paragraph.getCategory().getName());
    }

    public void testUpdate() {
        int paragraphId = 1;
        ParagraphService paragraphService = new ParagraphService();
        paragraphService.update("TitleChange", "2021-01-01", "TextChange", paragraphId, 3);
        Paragraph paragraph = paragraphService.selectParagraphByParagraphIdAndUserId(paragraphId, userId);

        Assert.assertEquals(new Integer(1), paragraph.getId());
        Assert.assertEquals("TitleChange", paragraph.getTitle());
        Assert.assertEquals("2021-01-01", paragraph.getDate());
        Assert.assertEquals("TextChange", paragraph.getText());
        Assert.assertEquals(new Integer(3), paragraph.getCategory().getId());

        paragraphService.update("HappyCamp", "2022-07-12", "HAPPY!!!", paragraphId, 7);
    }

    public void testSelectParagraphByCategoryIdAndUserId() {
        ParagraphService paragraphService = new ParagraphService();
        List<Paragraph> list = paragraphService.selectParagraphByCategoryIdAndUserId(7, userId);

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("HappyCamp", list.get(0).getTitle());
        Assert.assertEquals("2022-07-12", list.get(0).getDate());
        Assert.assertEquals("HAPPY!!!", list.get(0).getText());
        Assert.assertEquals(new Integer(7), list.get(0).getCategory().getId());
        Assert.assertEquals("For DAO Test", list.get(0).getCategory().getName());
    }
}