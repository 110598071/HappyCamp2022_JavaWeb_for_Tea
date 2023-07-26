package Servlet.User;

import Entity.User;
import Service.UserService;
import Servlet.Util.CookieServlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE = "WEB-INF/jsp/User/Login.jsp";
    private static final String PARAGRAPH_LIST = "ParagraphList";
    CookieServlet cookieServlet = new CookieServlet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("sessionAttribute", null);

        List<Cookie> userCookies = cookieServlet.getUserCookie(req.getCookies());
        Cookie accountCookie = userCookies.get(0);
        Cookie userIdCookie = userCookies.get(1);
        Cookie roleCookie = userCookies.get(2);

        if (cookieServlet.checkCookies(accountCookie, userIdCookie, roleCookie)) {
            req.getSession().setAttribute("account", accountCookie.getValue());
            req.getSession().setAttribute("userId", userIdCookie.getValue());
            req.getSession().setAttribute("role", roleCookie.getValue());

            resp.sendRedirect(PARAGRAPH_LIST);
        }
        else {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        UserService userService = new UserService();
        User user = userService.selectUserByAccountAndPasswordForSQLInjection(account, password);

        if(user != null) {
            req.getSession().setAttribute("account", user.getAccount());
            req.getSession().setAttribute("userId", user.getId());
            req.getSession().setAttribute("role", user.getRole());

            List<Cookie> userCookies = cookieServlet.getUserCookie(req.getCookies());
            Cookie accountCookie = userCookies.get(0);
            Cookie userIdCookie = userCookies.get(1);
            Cookie roleCookie = userCookies.get(2);

            if (cookieServlet.checkCookies(accountCookie, userIdCookie, roleCookie)) {
                if (userIdCookie.getValue().equals(Integer.toString(user.getId()))) {
                    System.out.println("same user");
                }
                else {
                    System.out.println("different user");
                    accountCookie.setValue(user.getAccount());
                    userIdCookie.setValue(Integer.toString(user.getId()));
                    roleCookie.setValue(Integer.toString(user.getRole()));
                }
            }
            else {
                System.out.println("new cookie");
                accountCookie = new Cookie("account", user.getAccount());
                userIdCookie = new Cookie("userId", Integer.toString(user.getId()));
                roleCookie = new Cookie("role", Integer.toString(user.getRole()));
            }
            resp.addCookie(accountCookie);
            resp.addCookie(userIdCookie);
            resp.addCookie(roleCookie);

            resp.sendRedirect(PARAGRAPH_LIST);
        }
        else {
            req.setAttribute("errorMsg", "Wrong Account or Password");
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }
}
