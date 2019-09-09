package by.javatr.library.dao;

import by.javatr.library.exception.DaoException;

import java.util.List;

public interface BookDao<T,K> extends Dao<T,K>  {
    boolean removeById(int id) throws DaoException;

}
