package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.UserBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.UserDao;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImp extends AbstractDao<User, String> implements UserDao<User, String> {
      private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE name = ? and password = ?";
    private final static String FIND_All_USERS = "SELECT * FROM users";


    public UserDaoImp(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = executeQuery(FIND_All_USERS, new UserBuilder());
        return users;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_USER_BY_LOGIN_AND_PASSWORD, new UserBuilder(), login, password);
    }

}
