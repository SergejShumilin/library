package by.javatr.library.builder.impl;

import by.javatr.library.builder.Builder;
import by.javatr.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBuilder implements Builder<Book> {
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        int id = resultSet.getInt(book.getId());
        book.setId(id);
        String title = resultSet.getString(book.getTitle());
        book.setTitle(title);
        String author = resultSet.getString(book.getAuthor());
        book.setAuthor(author);
        String genre = resultSet.getString(book.getGenre());
        book.setGenre(genre);
        int numberOfInstances = resultSet.getInt(book.getNumberOfInstances());
        book.setNumberOfInstances(numberOfInstances);
        return book;
    }
}
