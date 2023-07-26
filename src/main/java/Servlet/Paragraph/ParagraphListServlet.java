package Servlet.Paragraph;

import Entity.Category;
import Entity.Paragraph;
import Service.CategoryService;
import Service.ParagraphService;
import Servlet.Util.AuthorizationServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParagraphListServlet extends AuthorizationServlet {
    private static final String PARAGRAPH_LIST_URL = "WEB-INF/jsp/Paragraph/ParagraphList.jsp";
    private static final String MODIFY_PARAGRAPH_URL = "ModifyParagraph";
    private static final String PARAGRAPH_PAGE_URL = "ParagraphPage";
    private static final String ERROR_PAGE = "WEB-INF/jsp/ErrorPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPage = PARAGRAPH_LIST_URL;
        try {
            CategoryService categoryService = new CategoryService();
            List<Category> categoryList = categoryService.show();
            req.setAttribute("categoryList", categoryList);

            List<Paragraph> list;
            ParagraphService paragraphService = new ParagraphService();
            if (req.getSession().getAttribute("categoryId") == null) {
                list = paragraphService.selectParagraphByUserId(Integer.parseInt(req.getSession().getAttribute("userId").toString()));
                req.setAttribute("categoryId", 0);
            }
            else {
                int categoryId = (Integer)req.getSession().getAttribute("categoryId");
                req.setAttribute("categoryId", categoryId);

                if (categoryId == 0) {
                    list = paragraphService.selectParagraphByUserId(Integer.parseInt(req.getSession().getAttribute("userId").toString()));
                }
                else {
                    list = paragraphService.selectParagraphByCategoryIdAndUserId(categoryId, Integer.parseInt(req.getSession().getAttribute("userId").toString()));
                }
            }

//        List<Paragraph> filterList = new ArrayList<>();
//        if (req.getSession().getAttribute("nameSearch") != null) {
//            String nameSearch = ((String)req.getSession().getAttribute("nameSearch")).toLowerCase();
//            if (nameSearch.equals("")) {
//                req.getSession().setAttribute("nameSearch", null);
//                filterList = list;
//            }
//            else {
//                for (Paragraph paragraph : list) {
//                    if (paragraph.getTitle().toLowerCase().contains(nameSearch) || paragraph.getText().toLowerCase().contains(nameSearch)) {
//                        filterList.add(paragraph);
//                    }
//                }
//            }
//        }
//        else {
//            filterList = list;
//        }

            List<Paragraph> filterList = new ArrayList<>();
            if (req.getParameterMap().containsKey("nameSearch")) {
                String nameSearch = req.getParameter("nameSearch");
                for (Paragraph paragraph : list) {
                    if (paragraph.getTitle().toLowerCase().contains(nameSearch.toLowerCase()) || paragraph.getText().toLowerCase().contains(nameSearch.toLowerCase())) {
                        filterList.add(paragraph);
                    }
                }
                req.getSession().setAttribute("nameSearch", nameSearch);
            }
            else {
                req.getSession().setAttribute("nameSearch", null);
                filterList = list;
            }
            req.getSession().setAttribute("paragraphList", filterList);
        }
        catch (Exception e) {
            req.setAttribute("errorMsg", "something wrong with getting or setting data!");
            redirectPage = ERROR_PAGE;
        }
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if (op.equals("setCategory")) {
            int categoryId = Integer.parseInt(req.getParameter("categorySelect"));
            req.getSession().setAttribute("categoryId", categoryId);
        }
        else if (op.equals("modify")) {
            int paragraphId = Integer.parseInt(req.getParameter("paragraphId"));
            req.getSession().setAttribute("paragraphId", paragraphId);
            resp.sendRedirect(MODIFY_PARAGRAPH_URL);
        }
//        else if (op.equals("searchParagraph")) {
//            String nameSearch = req.getParameter("nameSearch");
//            req.getSession().setAttribute("nameSearch", nameSearch);
//        }
        else if (op.equals("gotoParagraphPage")) {
            int paragraphId = Integer.parseInt(req.getParameter("paragraphId"));
            req.getSession().setAttribute("paragraphId", paragraphId);
            resp.sendRedirect(PARAGRAPH_PAGE_URL);
        }
    }
}
