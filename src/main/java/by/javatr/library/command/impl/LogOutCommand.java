package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.exception.DaoException;
import by.javatr.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    private UserService userService;

    public LogOutCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoException {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login.jsp";
    }
}
