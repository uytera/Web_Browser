package com.filem.servlet;

import com.filem.tech.Accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet  extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        String stringFormat = "[a-zA-Z]+";
        String emailFormat = "/.+@.+\\..+/i";
        boolean a = login.matches(stringFormat);
        boolean b = pass.matches(stringFormat);
        boolean s = email.matches(emailFormat);
        if(!login.matches(stringFormat) || !pass.matches(stringFormat)){
            resp.getWriter().println("Irritum format. Tessera et alias oportet continent solum latinis");
            return;
        }
        if(login.length() < 3 || pass.length() < 3){
            resp.getWriter().println("Alias et tessera, oportet quod sit amplius quam tres characteres");
            return;
        }
        /*if(!email.matches(emailFormat)){
            resp.getWriter().println("Irritum mailing address");
            return;
        }*/
        if(accountService.AddNewUser(login, pass, email)){
            //req.setAttribute("path", "C:/");
            resp.sendRedirect("/login/");
            //getServletContext().getRequestDispatcher("/fileServl").forward(req, resp);
        }
        else {
            resp.getWriter().println("Talis login iam existit");
            return;
        }
    }
}
