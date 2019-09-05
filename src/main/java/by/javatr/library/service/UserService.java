package by.javatr.library.service;

import by.javatr.library.dao.impl.UserDaoImp;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserService {
    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    private UserDaoImp dao = new UserDaoImp();

    public UserService() {
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> user = null;
        try{
            user = dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e){
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }

    public boolean register(User user) throws ServiceException {
        try{
            dao.save(user);
        } catch (DaoException e){
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }


}
