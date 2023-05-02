package com.servlets;

import com.tools.DBManager;
import com.tools.Language;
import com.tools.NewsCategory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

//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

@WebServlet(value = "/addCategoryPage")
public class addCategoryPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String language = "";
        if(cookies!=null){
            for (Cookie c : cookies) {
                if (c.getName().equals("language")) {
                    language = c.getValue();
                    break;
                }
            }
        }else{
            Cookie cookieLanguage = new Cookie("language", "eng");
            response.addCookie(cookieLanguage);
            language = cookieLanguage.getValue();
        }
        request.setAttribute("languageString", language);

        ArrayList<NewsCategory> newsCategories = DBManager.getCategories();
        request.setAttribute("categories", newsCategories);
        request.getRequestDispatcher("/addCategoryPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
