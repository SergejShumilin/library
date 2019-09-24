package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ToEditCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            BookDaoImpl bookDao = new BookDaoImpl(connection);
            Optional<Book> byTitle = bookDao.findById(id);
            Book book = byTitle.get();
            request.setAttribute("book", book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return new CommandResult("/WEB-INF/jsp/edit.jsp", false);
    }
}
