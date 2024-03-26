package util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Данный утилитный класс нужен для выдачи соединения с БД
 */
@UtilityClass
public class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    /**
     * Метод, который загружает драйвер для работы с БД
     */
    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод, который выдаёт соединение с БД
     */
    public static Connection get() {
        try {
            return DriverManager.getConnection(PropertiesUtil.get(URL_KEY), PropertiesUtil.get(USER_KEY), PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
