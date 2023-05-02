package com.servlets;

import com.tools.DBManager;
import com.tools.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = DBManager.getUser(email);

        String redirect = "/loginPageServlet?emailerror";
        if(user!=null){
            redirect = "/loginPageServlet?passworderror";
            if(user.getPassword().equals(password)){
                request.getSession().setAttribute("CURRENT_USER", user);
                redirect = "/homeServlet";
            }
        }
        response.sendRedirect(redirect);
    }
}
