package by.javatr.library.command.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.command.Command;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Role;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddBookCommand implements Command {
    private BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/WEB-INF/jsp/main.jsp";
        String title = request.getParameter("title");
        try {
            Book book = bookService.findByTitle(title);
            if (book == null) {
                String author = request.getParameter("author");
                String genre = request.getParameter("genre");
                String description = request.getParameter("description");
                book = new Book(title, author, genre, description, 1);
                bookService.save(book);
            } else {
                int numberOfInstances = book.getNumberOfInstances();
                book.setNumberOfInstances(numberOfInstances + 1);
                bookService.update(book);
            }
            List<Book> allBooks = bookService.findAll();
            request.setAttribute("books", allBooks);
        } catch (ServiceException e){
            page = "/WEB-INF/jsp/error.jsp";
        }
        return page;
    }
}
