package by.javatr.library.controller;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandFactory;

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
        // определение команды, пришедшей из JSP
        String page = null;
        String action = request.getParameter("command");
        if (action!=null){
            Command command =CommandFactory.createCommand(action);
            page = command.execute(request);

        } else {
            page = "login.jsp";
        }
        request.getRequestDispatcher(page).forward(request, response);


    }
}
