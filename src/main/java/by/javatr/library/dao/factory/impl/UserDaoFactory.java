package by.javatr.library.dao.factory.impl;

import by.javatr.library.dao.Dao;
import by.javatr.library.dao.factory.DaoFactory;
import by.javatr.library.dao.impl.UserDao;

public class UserDaoFactory implements DaoFactory {
    @Override
    public Dao createDao() {
        return new UserDao();
    }
}
