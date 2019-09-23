package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ToEditCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
