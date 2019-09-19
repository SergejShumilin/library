package by.javatr.library.dao;

import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;

import java.util.Optional;

public interface BookDao<T,K> extends Dao<T,K>  {
    void updateBookNumber(Book book) throws DaoException;
    Optional<Book> findByTitle(String title) throws DaoException;
    void removeByTitle(String title) throws DaoException;
    void save(T entity) throws DaoException;
}
