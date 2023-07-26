package Service;

import Entity.Paragraph;
import Dao.ParagraphDAO;
import java.util.List;

public class ParagraphService {
    private final ParagraphDAO paragraphDAO = new ParagraphDAO();

    public List<Paragraph> selectAllParagraph() {
        return paragraphDAO.selectAllParagraph();
    }

    public List<Paragraph> selectParagraphByUserId(int userId) {
        return paragraphDAO.selectParagraphByUserId(userId);
    }

    public void insertParagraph(Paragraph paragraph, int categoryId, int userId) {
        paragraphDAO.insertParagraph(paragraph, categoryId, userId);
    }

    public void deleteParagraphByParagraphId(int paragraphId) {
        paragraphDAO.deleteParagraphByParagraphId(paragraphId);
    }

    public void deleteParagraphByUserId(int userId) {
        paragraphDAO.deleteParagraphByUserId(userId);
    }

    public void deleteParagraphByCategoryId(int categoryId) {
        paragraphDAO.deleteParagraphByCategoryId(categoryId);
    }

    public Paragraph selectParagraphByParagraphIdAndUserId(int paragraphId, int userId) {
        return paragraphDAO.selectParagraphByParagraphIdAndUserId(paragraphId, userId);
    }

    public void update(String title, String date, String text, int paragraphId, int CategoryId) {
        paragraphDAO.update(title, date, text, paragraphId, CategoryId);
    }

    public List<Paragraph> selectParagraphByCategoryIdAndUserId(int categoryId, int userId) {
        return paragraphDAO.selectParagraphByCategoryIdAndUserId(categoryId, userId);
    }
}
