package by.javatr.library.dao;

import by.javatr.library.builder.Builder;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T, K> implements Dao<T, K> {
    private final static Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected List<T> executeQuery(String query, Builder<T> builder) throws DaoException {
        PreparedStatement preparedStatement = null;
        ProxyConnection connection = null;
        List<T> entities = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
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
        return entities;
    }

    protected ProxyConnection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }

}
