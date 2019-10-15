package com.filem.servlet;

import com.filem.tech.Accounts.AccountService;
import com.filem.tech.Accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        UserProfile authProfile = new UserProfile(login, pass, email);

        if(accountService.AuthorizateUser(authProfile, req.getSession().getId())){
            //req.setAttribute("path", "C:/");
            resp.sendRedirect("/FileBrowser");
            //getServletContext().getRequestDispatcher("/fileServl").se(req, resp);
        }
        else{
            resp.getWriter().println("Recte data ingressus");
            return;
        }
    }
}
