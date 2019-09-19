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

public class DeleteBookCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);
    private BookService bookService;

    public DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = Constants.MAIN;
        String title = request.getParameter("title");
        try {
            Optional<Book> bookOptional = bookService.findByTitle(title);
            if (bookOptional.isPresent()){
                Book book = bookOptional.get();
                int numberOfInstances = book.getNumberOfInstances();
                if (numberOfInstances > 1) {
                    book.setNumberOfInstances(numberOfInstances - 1);
                    bookService.update(book);
                } else {
                    bookService.delete(book);
                }
                List<Book> allBooks = bookService.findAll();
                request.setAttribute("books", allBooks);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            page = Constants.ERROR;
        }
        return page;
    }
}
