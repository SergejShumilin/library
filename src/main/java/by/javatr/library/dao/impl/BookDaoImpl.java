package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.BookDao;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class BookDaoImpl extends AbstractDao<Book, String> implements BookDao<Book, String> {
    private final static Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    private final static String ADD_BOOK = "insert into books(title,author,genre,description,number_instances) values (?,?,?,?,?)";
    private final static String DELETE_BOOK = "DELETE FROM books WHERE title = ?";
    private final static String FIND_All_BOOKS = "SELECT * FROM books";
    private final static String FIND_BOOK = "SELECT * FROM books WHERE title = ?";
    private static String UPDATE_BOOK_STOCK = "UPDATE books SET number_instances=? WHERE title=?";

    public BookDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Book> findByTitle(String title) throws DaoException {
        return executeForSingleResult(FIND_BOOK, new BookBuilder(), title);
    }

    @Override
    public List<Book> findAll() throws DaoException {
        return executeQuery(FIND_All_BOOKS, new BookBuilder());
    }

    @Override
    public void save(Book book) throws DaoException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setInt(5, book.getNumberOfInstances());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void removeByTitle(String title) throws DaoException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void updateBookNumber(Book book) throws DaoException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_STOCK);
            preparedStatement.setInt(1, book.getNumberOfInstances());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
