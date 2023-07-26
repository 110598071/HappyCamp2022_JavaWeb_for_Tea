package Servlet.User;

import Service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private static final String SIGNUP_URL = "WEB-INF/jsp/User/Signup.jsp";
    private static final String LOGIN_URL = "WEB-INF/jsp/User/Login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGNUP_URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        UserService userService = new UserService();
        try {
            userService.insertUser(2, account, password);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
