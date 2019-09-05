package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.UserBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.UserDao;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImp extends AbstractDao<User, String> implements UserDao<User, String> {
    private final static Logger LOGGER = Logger.getLogger(UserDaoImp.class);

    private final static String ADD_USER = "insert into users(name,password) values (?,?)";
    private final static String FIND_USER = "SELECT * FROM users WHERE name = ?";
    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE name = ? and password = ?";
    private final static String FIND_All_USERS = "SELECT * FROM users";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = executeQuery(FIND_All_USERS, new UserBuilder());
        return users;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        ProxyConnection connection = null;
        User user = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 user = new UserBuilder().build(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        if (user==null){
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public boolean save(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                if (connection!=null){
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return true;
    }

    @Override
    public Optional<User> getByName(String name) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            connection  = getConnection();
            preparedStatement = connection.prepareStatement(FIND_USER);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new UserBuilder().build(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } finally {
            if (connection != null){
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        if (user==null){
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
