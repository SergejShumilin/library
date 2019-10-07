package by.javatr.library.command.impl.order;

import by.javatr.library.command.AbstractOrderCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand extends AbstractOrderCommand {

    public CancelOrderCommand(OrderService orderService) {
        super(orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.updateOrderById(id);
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        returnBook(bookId);
//        return new CommandResult(Constants.MAIN_COMMAND, true);
        return new CommandResult(Constants.SHOW_USER_COMMAND, true);
    }
}
