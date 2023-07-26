package Servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthorizationServlet extends HttpServlet {
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieServlet cookieServlet = new CookieServlet();
        List<Cookie> userCookies = cookieServlet.getUserCookie(req.getCookies());
        if (!(cookieServlet.checkCookies(userCookies.get(0), userCookies.get(1), userCookies.get(2)) && (userCookies.get(2).getValue().equals("1") || isUserPage()))) {
            req.setAttribute("errorMsg", "Insufficient Permissions!");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        super.service(req, resp);
    }

    protected boolean isUserPage(){
        return true;
    }
}
