package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.logic.LoginService;
import by.javatr.library.command.logic.LoginServiceImpl;
import by.javatr.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        LoginService service = new LoginServiceImpl();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        Optional<User> user = service.checkLogin(login, password);
        user.ifPresent(u -> request.setAttribute("user", login));

        return "main.jsp";
    }
}
