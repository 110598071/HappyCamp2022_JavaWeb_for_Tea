package Servlet.Category;

import Entity.Category;
import Entity.Paragraph;
import Service.CategoryService;
import Service.ParagraphService;
import Servlet.Util.AuthorizationServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryListServlet extends AuthorizationServlet {
    private static final String CATEGORY_LIST_URL = "WEB-INF/jsp/Category/CategoryList.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPage = CATEGORY_LIST_URL;
        try {
            CategoryService categoryService = new CategoryService();
            List<Category> categoryList = categoryService.show();

            int userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
            ParagraphService paragraphService = new ParagraphService();
            List<Paragraph> paragraphList;
            if (userId == 1) {
                paragraphList = paragraphService.selectAllParagraph();
            }
            else {
                paragraphList = paragraphService.selectParagraphByUserId(userId);
            }

            List<Category> categoryWithParagraphAmountList = categoryService.countParagraphFroCategory(categoryList, paragraphList);
            req.getSession().setAttribute("categoryWithParagraphAmountList", categoryWithParagraphAmountList);
        }
        catch (Exception e) {
            req.setAttribute("errorMsg", "something wrong with getting or setting data!");
            redirectPage = ERROR_PAGE;
        }
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if (op.equals("deleteCategory")) {
            int categoryId = Integer.parseInt(req.getParameter("categoryId").substring(6));

            ParagraphService paragraphService = new ParagraphService();
            paragraphService.deleteParagraphByCategoryId(categoryId);

            CategoryService categoryService = new CategoryService();
            categoryService.deleteCategoryById(categoryId);
        }
        else if (op.equals("addCategory")) {
            String categoryName = req.getParameter("categoryName");
            CategoryService categoryService = new CategoryService();
            categoryService.insertCategory(categoryName);
        }
    }
}
