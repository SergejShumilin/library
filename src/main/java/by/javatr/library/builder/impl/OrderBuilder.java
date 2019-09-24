package by.javatr.library.builder.impl;

import by.javatr.library.builder.Builder;
import by.javatr.library.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderBuilder implements Builder {
    @Override
    public Orders build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("name");
        String author = resultSet.getString("title");
        boolean active = resultSet.getBoolean("active");
        Orders orders = new Orders(id, title, author, active);
        return orders;
    }
}
