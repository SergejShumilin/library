package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.dao.impl.UserDao;
import by.javatr.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

public class RegistrationCommand implements Command {
    private AtomicInteger idGenerator = new AtomicInteger();
    private UserDao dao = new UserDao();

    @Override
    public String execute(HttpServletRequest request) {
        String page =null;
        int id = idGenerator.incrementAndGet();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        if (password.equals(passwordConfirm)) {
            User user = new User(id, name, password);
            dao.save(user);
            page = "login.jsp";
        } else {
//            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
