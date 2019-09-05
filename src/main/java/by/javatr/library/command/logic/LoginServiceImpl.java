package by.javatr.library.command.logic;

import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASS = "123";

    public Optional<User> checkLogin(String enterLogin, String enterPass) {
        User user = null;
        if (ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass)) {
            user = new User();
        } else {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
