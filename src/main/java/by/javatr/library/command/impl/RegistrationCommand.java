package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page =null;
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        if (password.equals(passwordConfirm)) {
            User user = new User(name, password);
            try {
                service.register(user);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
            }
            page = "login.jsp";
        } else {
            page = "/WEB-INF/jsp/error.jsp";
        }
        return page;
    }
}
