package by.javatr.library.dao;

import by.javatr.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {
    List<T> findAll() throws DaoException;
    Optional<T> getByName(K name) throws DaoException;
    boolean save(T entity) throws DaoException;

}
