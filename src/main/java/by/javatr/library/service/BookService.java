package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final static Logger LOGGER = Logger.getLogger(BookService.class);

    private DaoFactory daoFactory;

    public BookService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Book> findAll() throws ServiceException {
        List<Book> books = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            books = bookDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return books;
    }

    public void update(Book book) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.updateBookNumber(book);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Book> findByTitle(String title) throws ServiceException {
        Optional<Book> book = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            book = bookDao.findByTitle(title);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return book;
    }

    public void save(Book book) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.save(book);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void delete(Book book) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.removeByTitle(book.getTitle());
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
