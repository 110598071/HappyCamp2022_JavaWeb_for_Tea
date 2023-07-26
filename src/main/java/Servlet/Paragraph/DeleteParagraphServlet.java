package Servlet.Paragraph;

import Service.ParagraphService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteParagraphServlet extends HttpServlet {
    private static final String PARAGRAPH_LIST_URL = "ParagraphList";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(PARAGRAPH_LIST_URL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paragraphId = req.getParameter("paragraphId").substring(6);
        int paragraphIdToInt = Integer.parseInt(paragraphId);

        ParagraphService paragraphService = new ParagraphService();
        paragraphService.deleteParagraphByParagraphId(paragraphIdToInt);
    }
}
