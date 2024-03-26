package service;

import lombok.SneakyThrows;
import util.PropertiesUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * Сервис, который занимается обработкой фотографией
 */
public class ImageService {
    /**
     * Путь, где хранится все фотографии с проекта
     */
    private final String basePath = PropertiesUtil.get("image.base.url");
    /**
     * Реализация singleton
     */
    private static final ImageService INSTANCE = new ImageService();

    /**
     * Реализация singleton
     */
    public static ImageService getInstance() {
        return INSTANCE;
    }

    /**
     * Реализация singleton
     */
    private ImageService() {
    }

    /**
     * Данный метод сохраняет аватарку пользователя на сервер (в моём случае на диск моего компьютера)
     */
    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        // получаем полный путь до файла
        Path imageFullPath = Path.of(basePath, imagePath);
        System.out.println(imageFullPath);
        // пытаемся записать его на диск по адресу выше
        try (imageContent) {
            // создаём родительские папки, если они ещё были не созданы
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }


    /**
     * Данный метод возвращает поток байт для фотографии по пути imagePath
     */
    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        Path imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }
}
