package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;

import java.util.List;

public class BookService {

    private DaoFactory daoFactory;

    public BookService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Book> findAll(){
        List<Book> books = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            books = bookDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return books;
    }
}
