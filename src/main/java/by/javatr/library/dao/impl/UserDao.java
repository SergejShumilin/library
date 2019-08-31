package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.UserBuilder;
import by.javatr.library.connection.ConnectionPool;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.Dao;
import by.javatr.library.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractDao<User, String> implements Dao<User, String> {

    private final static String ADD_USER = "insert into users(name,password,role) values (?,?,?)";
    private final static String FIND_USER = "SELECT * FROM users WHERE name = ?";
    private final static String FIND_All_USERS = "SELECT * FROM users";

    private Connection connection;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<User> findAll() {
        List<User> users = executeQuery(FIND_All_USERS, new UserBuilder());
        return users;
    }

    @Override
    public User getByName(String name) {
        User user = executeQuerySingleResult(FIND_USER, new UserBuilder(), name);
        return user;
    }

    @Override
    public boolean remove(User entity) {
        return false;
    }

    @Override
    public boolean save(User user) {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        } finally {
            connectionPool.closeConnection(connection);
        }
        return true;
    }
}
