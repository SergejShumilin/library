package by.javatr.library.command;

import by.javatr.library.command.impl.LogOutCommand;
import by.javatr.library.command.impl.LoginCommand;
import by.javatr.library.dao.DaoFactory;
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
            case"language":
                return new LanguageCommand();
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
