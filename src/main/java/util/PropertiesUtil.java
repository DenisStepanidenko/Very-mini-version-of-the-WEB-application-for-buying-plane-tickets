package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Данный утилитный класс возвращает значения из файла application.properties для входа в БД
 */
public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    /**
     * Данный метод загружает в объект типа Properties файл application.properties
     */
    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {
    }

    /**
     * Возвращает значение из Properties по ключу key
     */
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
