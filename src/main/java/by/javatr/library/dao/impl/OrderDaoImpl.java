package by.javatr.library.dao.impl;

import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.OrderDao;
import by.javatr.library.entity.Order;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order, String> implements OrderDao<Order, String> {
    private final static Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    private final static String ADD_ORDER = "insert into orders(user_id,book_id) values (?,?)";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Order order) throws DaoException {
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getBookId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return true;
    }


    @Override
    public List findAll() throws DaoException {
        return null;
    }


}
