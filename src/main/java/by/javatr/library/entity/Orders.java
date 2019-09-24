package by.javatr.library.entity;

public class Orders {
    private int id;
    private String name;
    private String title;
    private boolean isActive;


    public Orders(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public Orders(int id, String name, String title, boolean isActive) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
