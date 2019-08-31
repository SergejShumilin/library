package by.javatr.library.dao;

import by.javatr.library.builder.Builder;
import by.javatr.library.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T,K> implements Dao<T, K> {

    protected List<T> executeQuery(String query, Builder<T> builder){
        ConnectionPool connectionPool = null;
        Connection connection = null;
        List<T> entities = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            entities = new ArrayList<>();
            while (resultSet.next()){
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {

        } finally {
            if (connectionPool!=null){
                connectionPool.closeConnection(connection);
            }
        }
        return entities;
    }

    protected T executeQuerySingleResult(String query, Builder<T> builder, String...parameters){
        ConnectionPool connectionPool = null;
        Connection connection = null;
        T entity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                entity = builder.build(resultSet);
            }
        } catch (SQLException e) {

        } finally {
            if (connectionPool!=null){
                connectionPool.closeConnection(connection);
            }
        }
        return entity;
    }
}
