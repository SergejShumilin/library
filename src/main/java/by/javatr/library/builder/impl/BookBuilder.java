package by.javatr.library.builder.impl;

import by.javatr.library.builder.Builder;
import by.javatr.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBuilder implements Builder<Book> {
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        String title = resultSet.getString("title");
        book.setTitle(title);
        String author = resultSet.getString("author");
        book.setAuthor(author);
        String genre = resultSet.getString("genre");
        book.setGenre(genre);
        int numberOfInstances = resultSet.getInt("number_instances");
        book.setNumberOfInstances(numberOfInstances);
        return book;
    }
}
