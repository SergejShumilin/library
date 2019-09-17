package by.javatr.library.controller;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandFactory;
import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String action = request.getParameter("command");
        
        if (action != null) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            try(ProxyConnection connection = connectionPool.getConnection()) {
                DaoFactory daoFactory = new DaoFactory(connection);
                CommandFactory commandFactory = new CommandFactory(daoFactory);
                Command command = commandFactory.createCommand(action);
                page = command.execute(request);
            } 
            catch (DaoException e) {
                request.setAttribute("error", e.getMessage());
                page = "/WEB-INF/jsp/error.jsp";
            }
        }
        else {
            page = "login.jsp";
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
