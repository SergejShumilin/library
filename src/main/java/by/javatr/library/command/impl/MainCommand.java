package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MainCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        try (ProxyConnection con = ConnectionPool.getInstance().getConnection()) {
            BookDaoImpl bookDao = new BookDaoImpl(con);
            List<Book> allBooks = bookDao.findAll();
            request.setAttribute("books", allBooks);
            page = Constants.MAIN;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new CommandResult(page, false);
    }
}
