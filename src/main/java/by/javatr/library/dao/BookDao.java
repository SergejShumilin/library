package by.javatr.library.dao;

import by.javatr.library.exception.DaoException;

public interface BookDao<T,K> extends Dao<T,K>  {
    boolean removeById(int id) throws DaoException;
}
