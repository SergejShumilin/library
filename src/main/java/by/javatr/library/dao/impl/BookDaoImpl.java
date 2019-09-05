package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.BookDao;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl extends AbstractDao<Book, String> implements BookDao<Book, String> {
    private final static Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    private final static String ADD_BOOK = "insert into books(title,author,genre,number_instance) values (?,?,?,?)";
    private final static String DELETE_BOOK = "DELETE FROM books WHERE id = ?";
    private final static String FIND_BOOK_BY_NAME = "SELECT * FROM books WHERE name = ?";
    private final static String FIND_All_BOOKS = "SELECT * FROM books";

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = executeQuery(FIND_All_BOOKS, new BookBuilder());
        return books;
    }

    @Override
    public Optional<Book> getByName(String name) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        Book book = null;
        try {
            connection  = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BOOK_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = new BookBuilder().build(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } finally {
            if (connection != null){
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        if (book==null){
            return Optional.empty();
        }
        return Optional.of(book);
    }


    @Override
    public boolean save(Book book) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement= null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setInt(3, book.getNumberOfInstances());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                if (connection!=null){
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return true;
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                if (connection!=null){
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return true;
    }
}
