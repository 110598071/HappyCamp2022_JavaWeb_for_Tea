package Service;

import Entity.Category;
import Entity.Paragraph;
import Dao.CategoryDAO;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> show() {
        return categoryDAO.show();
    }

    public List<Category> countParagraphFroCategory(List<Category> categoryList, List<Paragraph> paragraphList) {
        List<Category> resultList = new ArrayList<>();
        int count;
        for (Category category : categoryList) {
            count = 0;
            for (Paragraph paragraph : paragraphList) {
                if (category.getId().equals(paragraph.getCategory().getId())) {
                    count += 1;
                }
            }
            category.setAmountOfParagraph(count);
            resultList.add(category);
        }
        return resultList;
    }

    public void insertCategory(String categoryName) {
        categoryDAO.insertCategory(categoryName);
    }

    public void deleteCategoryById(int categoryId) {
        categoryDAO.deleteCategoryById(categoryId);
    }
}
