package by.javatr.library.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final static ConnectionPool INSTANCE = new ConnectionPool(20);

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private BlockingQueue<Connection> connectionQueue;

    public ConnectionPool(final int POOL_SIZE) {
        DbConnection connectorDataBase = new DbConnection();
        connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection proxyConnection = null;
            try {
                proxyConnection = new ProxyConnection(connectorDataBase.makeConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (proxyConnection != null) {
                connectionQueue.offer(proxyConnection);
            }
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        connectionQueue.offer(connection);
    }
}
