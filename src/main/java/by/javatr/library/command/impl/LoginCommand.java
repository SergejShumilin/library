package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public class LoginCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
//        Optional<User> userOptional = null;
        try {
            Optional<User> userOptional = userService.login(login, password);
            User user = null;
            if (userOptional.isPresent()){
                user = userOptional.get();
            }
            if (user==null) {
//                request.setAttribute("errorLoginPassMessage", true);
                request.setAttribute("errorLoginPassMessage", "Incorrect login or password");
                page = "login.jsp";
            } else {
                session.setAttribute("user", user);
                BookDaoImpl bookDao = new BookDaoImpl(ConnectionPool.getInstance().getConnection());
                List<Book> allBooks = bookDao.findAll();
                if (Role.ADMIN.equals(user.getRole())){
                        request.setAttribute("books", allBooks);
                        page = "/WEB-INF/jsp/main.jsp";
                }
                else if (Role.READER.equals(user.getRole())){
                    request.setAttribute("books", allBooks);
                    page = "/WEB-INF/jsp/reader.jsp";
                }
            }
        } catch (ServiceException | DaoException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return page;
    }

}
