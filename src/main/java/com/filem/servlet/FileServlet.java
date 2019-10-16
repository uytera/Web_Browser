package com.filem.servlet;
import com.filem.tech.Accounts.AccountService;
import com.filem.tech.FilesList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FileBrowser")
public class FileServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        String login;

        if(!accountService.CheckSessionId(sessionId)){
            resp.getWriter().println("Advena. Commodo signum in");
            return;
        }
        else{
            login = accountService.getLoginBySessionId(sessionId);
        }
        String defaultPath = "C:\\Users" + "\\" + login;
        if (req.getParameter("path") != null) {
            defaultPath = req.getParameter("path");
        }
        FilesList filesList = new FilesList();

        if (filesList.setLists(defaultPath)) {
            req.setAttribute("login", accountService.getLoginBySessionId(sessionId));
            req.setAttribute("dirs", filesList.getDirs());
            req.setAttribute("files", filesList.getFiles());
            if(defaultPath.compareTo("C:\\Users" + "\\" + login) != 0) {
                req.setAttribute("parentsPath", filesList.getPath());
            }
            getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);
        }
        else{
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            return;
        }
    }
}
