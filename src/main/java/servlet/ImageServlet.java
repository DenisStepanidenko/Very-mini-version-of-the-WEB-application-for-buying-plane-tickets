package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.ImageService;

import java.io.IOException;
import java.io.InputStream;


/**
 * Сервлет, который по адресу изображения на компьютере возвращает его
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    /**
     * Зависимость для ImageService
     */
    private final ImageService imageService = ImageService.getInstance();


    /**
     * Принимает путь до фотографии конкретного пользователя и возвращает картинку по этому адресу
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        imageService.get(req.getParameter("pathToPhoto")).ifPresentOrElse(image -> {
            resp.setContentType("application/octet-stream");
            writeImage(image, resp);
        }, () -> resp.setStatus(404));

    }

    /**
     * Метод, который записывает в выходной поток байт фотографии
     */
    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image; ServletOutputStream outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}
