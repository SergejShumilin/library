package by.javatr.library.command;

import by.javatr.library.command.impl.GoToRegisterCommand;
import by.javatr.library.command.impl.LoginCommand;
import by.javatr.library.command.impl.RegistrationCommand;


public class CommandFactory {

    public Command createCommand(String action) {
        switch (action) {
            case "login":
                return new LoginCommand();
            case "goToRegister":
                return new GoToRegisterCommand();
            case "register":
                return new RegistrationCommand();
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
