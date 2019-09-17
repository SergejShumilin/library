package by.javatr.library.dao.connection;

import java.util.ResourceBundle;

public class DbPropertyManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private DbPropertyManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
