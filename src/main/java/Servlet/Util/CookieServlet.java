package Servlet.Util;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class CookieServlet {
    public Cookie getCookieByName(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public List<Cookie> getUserCookie(Cookie[] cookies) {
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(getCookieByName(cookies, "account"));
        cookieList.add(getCookieByName(cookies, "userId"));
        cookieList.add(getCookieByName(cookies, "role"));
        return cookieList;
    }

    public boolean checkCookies(Cookie accountCookie, Cookie userIdCookie, Cookie roleCookie) {
        return accountCookie != null && userIdCookie != null && roleCookie != null;
    }
}
