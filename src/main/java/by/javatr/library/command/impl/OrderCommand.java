package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(OrderCommand.class);
    private OrderService orderService;

    public OrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        int bookId = Integer.parseInt(request.getParameter("id"));
        Order order = new Order(userId, bookId);
        try {
            orderService.save(order);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Constants.ERROR;
        }

        return Constants.READER;
    }
}
