package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.UserService;
import by.javatr.library.util.Constants;
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
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            Optional<User> userOptional = userService.login(login, password);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                HttpSession session = request.getSession();
                int userId = user.getId();
                session.setAttribute("userId", userId);
                try (ProxyConnection con = ConnectionPool.getInstance().getConnection()) {
                    BookDaoImpl bookDao = new BookDaoImpl(con);
                    List<Book> allBooks = bookDao.findAll();
                    request.setAttribute("books", allBooks);
                }
                if (Role.ADMIN.equals(user.getRole())){
                    page = Constants.MAIN;
                }
                else if (Role.READER.equals(user.getRole())){
                    page = Constants.READER;
                }
            } else {
                request.setAttribute("errorLoginPassMessage", true);
                page = Constants.LOGIN;

            }
        }
        catch ( DaoException e) {
            LOGGER.error(e.getMessage(), e);
            page = Constants.ERROR;
        }
        return new CommandResult(page, false);
    }
}
