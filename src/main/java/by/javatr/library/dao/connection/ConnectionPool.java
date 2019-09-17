package by.javatr.library.dao.connection;

import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import static by.javatr.library.dao.connection.DbPropertyManager.getProperty;

public class ConnectionPool {
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private LinkedBlockingQueue<ProxyConnection> connectionQueue;

    private final static String URL = getProperty("url");
    private final static String USER = getProperty("user");
    private final static String PASSWORD = getProperty("password");
    private final static int POOL_SIZE = Integer.parseInt(getProperty("poolSize"));
    private static final ConnectionPool INSTANCE = new ConnectionPool(POOL_SIZE);

    private ConnectionPool(int poolSize) {
        connectionQueue = new LinkedBlockingQueue<>(poolSize);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("ConnectionPool(int poolSize)", e);
            throw new RuntimeException(e);
        }
        for (int i = 0; i < poolSize; i++) {
            connectionQueue.offer(createConnection());
        }
        if (connectionQueue.size() < poolSize * 0.75) {
            LOGGER.fatal("ConnectionPool don`t created");
            throw new RuntimeException();
        }
    }

    /**
     * @return new ProxyConnection
     */
    private ProxyConnection createConnection() {
        ProxyConnection proxyConnection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            proxyConnection = new ProxyConnection(connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return proxyConnection;
    }

    /**
     * @return instance of Connection pool
     */

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    /**
     * @return ProxyConnection from pool
     */
    public ProxyConnection getConnection() throws DaoException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return connection;
    }

    /**
     * @param connection return connection to pool
     */
    public void closeConnection(ProxyConnection connection) {
        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            connectionQueue.offer(createConnection());
        }
    }

    /**
     * close all connections in pool
     */
    @PreDestroy
    public void closeAll() {
        connectionQueue.forEach(ProxyConnection::doClose);
    }

}

