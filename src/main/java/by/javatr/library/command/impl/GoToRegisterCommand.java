package by.javatr.library.command.impl;

import by.javatr.library.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GoToRegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "registration.jsp";
    }
}
