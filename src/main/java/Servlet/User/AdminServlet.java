package Servlet.User;

import Entity.User;
import Service.ParagraphService;
import Service.UserService;
import Servlet.Util.AuthorizationServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends AuthorizationServlet {
    private static final String ADMIN_URL = "WEB-INF/jsp/User/Admin.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected boolean isUserPage(){
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPage = ADMIN_URL;
        try {
            UserService userService = new UserService();
            List<User> users = userService.selectAllUser();
            req.getSession().setAttribute("users", users);
        }
        catch (Exception e) {
            req.setAttribute("errorMsg", "something wrong with getting or setting data!");
            redirectPage = ERROR_PAGE;
        }
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        ParagraphService paragraphService = new ParagraphService();

        String op = req.getParameter("op");
        if (op.equals("addUser")) {
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            int role = Integer.parseInt(req.getParameter("role"));

            try {
                userService.insertUser(role, account, password);
            }
            catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        else if (op.equals("deleteUser")) {
            int userId = Integer.parseInt(req.getParameter("userId").substring(6));
            paragraphService.deleteParagraphByUserId(userId);
            userService.deleteUserById(userId);
        }
    }
}
