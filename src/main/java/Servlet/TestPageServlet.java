package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestPageServlet extends HttpServlet {
    private static final String TEST_PAGE_URL = "WEB-INF/jsp/TestPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestAttribute", "request attribute successfully obtained");
        req.getSession().setAttribute("sessionAttribute", "session attribute successfully obtained");
        req.getRequestDispatcher(TEST_PAGE_URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(TEST_PAGE_URL).forward(req, resp);
    }
}
