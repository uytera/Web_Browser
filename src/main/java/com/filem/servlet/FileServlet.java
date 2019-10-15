package com.filem.servlet;
import com.filem.tech.Accounts.AccountService;
import com.filem.tech.FilesList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
@WebServlet("/FileBrowser")
public class FileServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();

        if(!accountService.CheckSessionId(sessionId)){
            resp.getWriter().println("Advena. Commodo signum in");
            return;
        }
        String path = req.getParameter("path");
        if (req.getParameter("path") == null) {
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            return;
        }
        path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        FilesList filesList = new FilesList();
        if (filesList.setLists(path)) {
            req.setAttribute("login", accountService.getLoginBySessionId(sessionId));
            req.setAttribute("dirs", filesList.getDirs());
            req.setAttribute("files", filesList.getFiles());
            req.setAttribute("parentsPath", filesList.getPath());
            getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);
        }
        else{
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            return;
        }
    }
}
