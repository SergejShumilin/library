package by.javatr.library.dao;

import java.util.List;

public interface Dao<T, K> {
    List<T> findAll();
    T getByName(K name);
    boolean remove(T entity);
    boolean save(T entity);
}
