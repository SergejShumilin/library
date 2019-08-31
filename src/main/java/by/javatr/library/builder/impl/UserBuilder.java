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
        int id = resultSet.getInt(user.getId());
        user.setId(id);
        String name = resultSet.getString(user.getName());
        user.setName(name);
        String password = resultSet.getString(user.getPassword());
        user.setPassword(password);
        String roleUser = resultSet.getString(user.getRole().name());
        Role role = Role.valueOf(roleUser);
        user.setRole(role);
        return user;
    }
}
