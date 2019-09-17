package by.javatr.library.builder.impl;

import by.javatr.library.builder.Builder;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        String name = resultSet.getString("name");
        user.setName(name);
        String password = resultSet.getString("password");
        user.setPassword(password);
        String stringRole = resultSet.getString("role").toUpperCase();
        Role role = Role.valueOf(stringRole);
        user.setRole(role);
        return user;
    }
}
