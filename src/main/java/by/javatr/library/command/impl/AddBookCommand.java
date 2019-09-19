package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AddBookCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(AddBookCommand.class);
    private BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = Constants.MAIN;
        String title = request.getParameter("title");
        Book book = null;
        try {
            Optional<Book> bookOptional = bookService.findByTitle(title);
            if (bookOptional.isPresent()) {
                book = bookOptional.get();
                int numberOfInstances = book.getNumberOfInstances();
                book.setNumberOfInstances(numberOfInstances + 1);
                bookService.update(book);
            } else {
                String author = request.getParameter("author");
                String genre = request.getParameter("genre");
                String description = request.getParameter("description");
                book = new Book(title, author, genre, description, 1);
                bookService.save(book);
            }
            List<Book> allBooks = bookService.findAll();
            request.setAttribute("books", allBooks);
        } catch (ServiceException e) {
            page = Constants.ERROR;
            LOGGER.error(e.getMessage(), e);
        }
        return page;
    }
}
