package by.javatr.library.entity;

import java.util.Objects;

public class Order {
    private int id;
    private int userId;
    private int bookId;

    public Order() {
    }

    public Order(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                bookId == order.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookId);
    }
}
