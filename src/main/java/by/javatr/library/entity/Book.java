package by.javatr.library.entity;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String description;
    private int numberOfInstances;

    public Book() {
    }

    public Book(String title, String author, String genre, String description, int numberOfInstances) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.numberOfInstances = numberOfInstances;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfInstances == book.numberOfInstances &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, description, numberOfInstances);
    }
}
