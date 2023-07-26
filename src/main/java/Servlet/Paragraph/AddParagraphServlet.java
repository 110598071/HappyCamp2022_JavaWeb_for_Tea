package Servlet.Paragraph;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import Entity.Category;
import Entity.Paragraph;
import Service.CategoryService;
import Service.ParagraphService;
import Servlet.Util.AuthorizationServlet;

public class AddParagraphServlet extends AuthorizationServlet {
    private static final String ADD_PARAGRAPH_URL = "WEB-INF/jsp/Paragraph/AddParagraph.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPage = ADD_PARAGRAPH_URL;
        try {
            CategoryService categoryService = new CategoryService();
            List<Category> categoryList = categoryService.show();
            req.setAttribute("categoryList", categoryList);
        }
        catch (Exception e) {
            req.setAttribute("errorMsg", "something wrong with getting or setting data!");
            redirectPage = ERROR_PAGE;
        }
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        System.out.println(title +", "+ text +", "+ date +", "+ req.getParameter("categoryId"));

        Paragraph paragraph = new Paragraph();
        paragraph.setTitle(title);
        paragraph.setText(text);
        paragraph.setDate(date);

        ParagraphService paragraphService = new ParagraphService();
        paragraphService.insertParagraph(paragraph, categoryId, Integer.parseInt(req.getSession().getAttribute("userId").toString()));
    }
}
