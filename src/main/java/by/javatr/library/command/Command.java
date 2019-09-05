package by.javatr.library.command;

import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws DaoException;
}
