package by.javatr.library.command;

import by.javatr.library.command.impl.*;
import by.javatr.library.dao.DaoFactory;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.service.UserService;


public class CommandFactory {
    private DaoFactory daoFactory;

    public CommandFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Command createCommand(String action) {
        switch (action) {
            case "login":
                return new LoginCommand(new UserService(daoFactory));
            case "logout":
                return new LogOutCommand(new UserService(daoFactory));
            case "addBook":
                return new AddBookCommand(new BookService(daoFactory));
            case "delete":
                return new DeleteBookCommand(new BookService(daoFactory));
            case "order":
                return new OrderCommand(new OrderService(daoFactory));
            case "language":
                return new LanguageCommand();
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
