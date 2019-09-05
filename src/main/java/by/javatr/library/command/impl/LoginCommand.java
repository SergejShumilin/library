package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class LoginCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private UserService userService = new UserService();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public LoginCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        Optional<User> user = null;
        try {
            user = userService.login(login, password);
            if (user.isPresent()) {
                request.setAttribute("user", login);
                page = "/WEB-INF/jsp/main.jsp";
            } else {
                request.setAttribute("errorLoginPassMessage", "Incorrect login or password");
                page = "login.jsp";
            }

        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return page;
    }
}
