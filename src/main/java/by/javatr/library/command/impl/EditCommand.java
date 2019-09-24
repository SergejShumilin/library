package by.javatr.library.command.impl;

import by.javatr.library.builder.impl.BookBuilder;
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

public class EditCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre =request.getParameter("genre");
        String description = request.getParameter("description");
        int numberOfInstances = Integer.parseInt(request.getParameter("numberOfInstances"));
        Book book = new Book(id, title, author, genre, description, numberOfInstances);
        BookDaoImpl bookDao = null;
        try(ProxyConnection connection =ConnectionPool.getInstance().getConnection()) {
            bookDao = new BookDaoImpl(connection);
            bookDao.updateBook(book);
            List<Book> allBooks = bookDao.findAll();
            request.setAttribute("books", allBooks);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }

        return new CommandResult(Constants.MAIN, false);
    }
}
