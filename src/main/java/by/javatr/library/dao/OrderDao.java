package by.javatr.library.dao;

import by.javatr.library.entity.Order;
import by.javatr.library.exception.DaoException;

public interface OrderDao<T, K> extends Dao<T,K> {
    boolean save(Order order) throws DaoException;
}
