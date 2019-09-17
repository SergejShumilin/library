package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteBookCommand implements Command {
    private BookService bookService;

    public DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/WEB-INF/jsp/main.jsp";
        String title = request.getParameter("title");
        try {
            Book book = bookService.findByTitle(title);
            int numberOfInstances = book.getNumberOfInstances();
            if (numberOfInstances > 1) {
                book.setNumberOfInstances(numberOfInstances - 1);
                bookService.update(book);
            } else {
                bookService.delete(book);
            }
            List<Book> allBooks = bookService.findAll();
            request.setAttribute("books", allBooks);
        } catch (ServiceException e) {
            page = "/WEB-INF/jsp/error.jsp";
        }
        return page;
    }
}
