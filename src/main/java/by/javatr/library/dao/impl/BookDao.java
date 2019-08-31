package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.Dao;
import by.javatr.library.entity.Book;

import java.util.List;

public class BookDao extends AbstractDao<Book, String> implements Dao<Book, String> {
    private final static String FIND_All_BOOKS = "SELECT * FROM books";

    @Override
    public List<Book> findAll() {
        List<Book> books = executeQuery(FIND_All_BOOKS, new BookBuilder());
        return books;
    }

    @Override
    public Book getByName(String name) {
        return null;
    }

    @Override
    public boolean remove(Book entity) {
        return false;
    }

    @Override
    public boolean save(Book entity) {
        return false;
    }
}
