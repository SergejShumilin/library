package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Order;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import org.apache.log4j.Logger;

public class OrderService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory;

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void save(Order order) throws ServiceException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            orderDao.save(order);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void findAll() throws ServiceException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            orderDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
