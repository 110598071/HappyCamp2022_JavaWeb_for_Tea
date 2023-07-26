package Servlet.Paragraph;

import Entity.Paragraph;
import Service.ParagraphService;
import Servlet.Util.AuthorizationServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParagraphPageServlet extends AuthorizationServlet {
    private static final String PARAGRAPH_PAGE_URL = "WEB-INF/jsp/Paragraph/ParagraphPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPage = PARAGRAPH_PAGE_URL;
        try {
            int paragraphId = (Integer)req.getSession().getAttribute("paragraphId");

            ParagraphService paragraphService = new ParagraphService();
            Paragraph paragraph = paragraphService.selectParagraphByParagraphIdAndUserId(paragraphId, Integer.parseInt(req.getSession().getAttribute("userId").toString()));
            req.setAttribute("paragraph", paragraph);

            req.setAttribute("content", paragraph.getText().replaceAll("\n", "<br>"));
        }
        catch (Exception e) {
            req.setAttribute("errorMsg", "something wrong with getting or setting data!");
            redirectPage = ERROR_PAGE;
        }
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PARAGRAPH_PAGE_URL).forward(req, resp);
    }
}
