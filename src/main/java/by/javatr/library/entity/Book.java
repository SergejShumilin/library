package by.javatr.library.entity;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int numberOfInstances;

    public Book() {
    }

    public Book(int id, String title, String author, String genre, int numberOfInstances) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numberOfInstances = numberOfInstances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }
}
