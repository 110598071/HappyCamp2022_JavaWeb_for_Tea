package Servlet.Paragraph;

import Entity.Category;
import Entity.Paragraph;
import Service.CategoryService;
import Service.ParagraphService;
import Servlet.Util.AuthorizationServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ModifyParagraphServlet extends AuthorizationServlet {
    private static final String MODIFY_PARAGRAPH_URL = "WEB-INF/jsp/Paragraph/ModifyParagraph.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPage = MODIFY_PARAGRAPH_URL;
        try {
            HttpSession session = req.getSession();
            int paragraphId = (Integer)session.getAttribute("paragraphId");

            ParagraphService paragraphService = new ParagraphService();
            Paragraph paragraph = paragraphService.selectParagraphByParagraphIdAndUserId(paragraphId, Integer.parseInt(req.getSession().getAttribute("userId").toString()));
            req.setAttribute("paragraph", paragraph);

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

        HttpSession session = req.getSession();
        int paragraphId = (Integer)session.getAttribute("paragraphId");

        ParagraphService paragraphService = new ParagraphService();
        paragraphService.update(title, date, text, paragraphId, categoryId);
    }
}
