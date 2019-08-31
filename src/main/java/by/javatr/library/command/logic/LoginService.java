package by.javatr.library.command.logic;

import by.javatr.library.entity.User;

import java.util.Optional;

public interface LoginService {
    public Optional<User> checkLogin(String enterLogin, String enterPass);

}
