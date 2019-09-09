package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.BookDao;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoImpl extends AbstractDao<Book, String> implements BookDao<Book, String> {
    private final static Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    private final static String ADD_BOOK = "insert into books(title,author,genre,number_instance) values (?,?,?,?)";
    private final static String DELETE_BOOK = "DELETE FROM books WHERE id = ?";
    private final static String FIND_BOOK_BY_NAME = "SELECT * FROM books WHERE name = ?";
    private final static String FIND_All_BOOKS = "SELECT * FROM books";

    public BookDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = executeQuery(FIND_All_BOOKS, new BookBuilder());
        return books;
    }

    @Override
    public Optional<Book> getByName(String name) throws DaoException {
        return executeForSingleResult(FIND_BOOK_BY_NAME, new BookBuilder(), name);
    }


    @Override
    public boolean save(Book book) throws DaoException {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setInt(4, book.getNumberOfInstances());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new DaoException(e.getMessage(), e);
        }
        return true;
    }

}
