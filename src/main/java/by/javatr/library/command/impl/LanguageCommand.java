package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        Locale locale = new Locale(language);
        session.setAttribute("locale", locale);
        return Constants.LOGIN;
    }
}
