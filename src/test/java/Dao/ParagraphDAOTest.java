package Dao;

import Entity.Category;
import Entity.User;
import junit.framework.TestCase;
import Entity.Paragraph;
import org.junit.Assert;
import java.util.List;

public class ParagraphDAOTest extends TestCase {
    private final int userId = 1;

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSelectAllParagraph() {
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        List<Paragraph> list = paragraphDAO.selectAllParagraph();

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("HappyCamp", list.get(0).getTitle());
        Assert.assertEquals("2022-07-12", list.get(0).getDate());
        Assert.assertEquals("HAPPY!!!", list.get(0).getText());
        Assert.assertEquals(new Integer(7), list.get(0).getCategory().getId());
        Assert.assertEquals("For DAO Test", list.get(0).getCategory().getName());
    }

    public void testSelectParagraphByUserId() {
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        List<Paragraph> list = paragraphDAO.selectParagraphByUserId(userId);

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

        ParagraphDAO paragraphDAO = new ParagraphDAO();
        paragraphDAO.insertParagraph(paragraph, 3, userId);

        List<Paragraph> list = paragraphDAO.selectParagraphByUserId(userId);
        Assert.assertEquals("DAOTest", list.get(list.size()-1).getTitle());
        Assert.assertEquals("2022-02-28", list.get(list.size()-1).getDate().toString());
        Assert.assertEquals("DAOTestText", list.get(list.size()-1).getText());
        Assert.assertEquals(new Integer(3), list.get(list.size()-1).getCategory().getId());

        paragraphDAO.deleteParagraphByParagraphId(list.get(list.size()-1).getId());
    }

    public void testDeleteParagraphByParagraphId() {
        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DeleteTestByParagraphId");
        paragraph.setDate("2010-03-27");
        paragraph.setText("DeleteTestText");

        ParagraphDAO paragraphDAO = new ParagraphDAO();
        paragraphDAO.insertParagraph(paragraph, 5, userId);
        List<Paragraph> list = paragraphDAO.selectParagraphByUserId(1);
        int deleteParagraphId = list.get(list.size()-1).getId();

        paragraphDAO.deleteParagraphByParagraphId(deleteParagraphId);
        list = paragraphDAO.selectParagraphByUserId(userId);
        Assert.assertNotEquals(new Integer(deleteParagraphId), list.get(list.size()-1).getId());
    }

    public void testDeleteParagraphByCategoryId() {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insertCategory("DAOTest");
        List<Category> categoryList = categoryDAO.show();
        int categoryId = categoryList.get(categoryList.size()-1).getId();

        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DeleteTestByCategoryId");
        paragraph.setDate("2010-03-27");
        paragraph.setText("DeleteTestText");
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        paragraphDAO.insertParagraph(paragraph, categoryId, userId);

        paragraphDAO.deleteParagraphByCategoryId(categoryId);
        List<Paragraph> paragraphList = paragraphDAO.selectParagraphByCategoryIdAndUserId(categoryId, userId);
        Assert.assertEquals(0, paragraphList.size());

        categoryDAO.deleteCategoryById(categoryId);
    }

    public void testDeleteParagraphByUserId() {
        int categoryId = 7;

        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(2, "testAccount", "testPassword");
        List<User> userList = userDAO.selectAllUser();
        int userId = userList.get(userList.size()-1).getId();

        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("DeleteTestByUserId");
        paragraph.setDate("2010-03-27");
        paragraph.setText("DeleteTestText");
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        paragraphDAO.insertParagraph(paragraph, categoryId, userId);

        paragraphDAO.deleteParagraphByUserId(userId);
        List<Paragraph> paragraphList = paragraphDAO.selectParagraphByCategoryIdAndUserId(categoryId, userId);
        Assert.assertEquals(0, paragraphList.size());

        userDAO.deleteUserById(userId);
    }

    public void testSelectParagraphByParagraphIdAndUserId() {
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        Paragraph paragraph = paragraphDAO.selectParagraphByParagraphIdAndUserId(1, userId);

        Assert.assertEquals(new Integer(1), paragraph.getId());
        Assert.assertEquals("HappyCamp", paragraph.getTitle());
        Assert.assertEquals("2022-07-12", paragraph.getDate());
        Assert.assertEquals("HAPPY!!!", paragraph.getText());
        Assert.assertEquals(new Integer(7), paragraph.getCategory().getId());
        Assert.assertEquals("For DAO Test", paragraph.getCategory().getName());
    }

    public void testUpdate() {
        int paragraphId = 1;
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        paragraphDAO.update("TitleChange", "2021-01-01", "TextChange", paragraphId, 3);
        Paragraph paragraph = paragraphDAO.selectParagraphByParagraphIdAndUserId(paragraphId, userId);

        Assert.assertEquals(new Integer(1), paragraph.getId());
        Assert.assertEquals("TitleChange", paragraph.getTitle());
        Assert.assertEquals("2021-01-01", paragraph.getDate());
        Assert.assertEquals("TextChange", paragraph.getText());
        Assert.assertEquals(new Integer(3), paragraph.getCategory().getId());

        paragraphDAO.update("HappyCamp", "2022-07-12", "HAPPY!!!", paragraphId, 7);
    }

    public void testSelectParagraphByCategoryIdAndUserId() {
        ParagraphDAO paragraphDAO = new ParagraphDAO();
        List<Paragraph> list = paragraphDAO.selectParagraphByCategoryIdAndUserId(7, userId);

        Assert.assertEquals(new Integer(1), list.get(0).getId());
        Assert.assertEquals("HappyCamp", list.get(0).getTitle());
        Assert.assertEquals("2022-07-12", list.get(0).getDate());
        Assert.assertEquals("HAPPY!!!", list.get(0).getText());
        Assert.assertEquals(new Integer(7), list.get(0).getCategory().getId());
        Assert.assertEquals("For DAO Test", list.get(0).getCategory().getName());
    }
}