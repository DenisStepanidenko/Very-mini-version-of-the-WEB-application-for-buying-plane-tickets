package servlet;

import dto.CreateUserDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.UserService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;


/**
 * Сервлет, который обрабатывает регистрацию пользователей
 */
@WebServlet("/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
public class RegistrationServlet extends HttpServlet {
    /**
     * Зависимость для UserService
     */
    private final UserService userService = UserService.getInstance();


    /**
     * Данный метод возвращает страничку для регистрации
     */
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        // сохраняем в атрибуты список с возможными гендерами
        req.setAttribute("gender", List.of("MALE", "FEMALE"));

        // Здесь перенаправляем полностью запрос с помощью forward на registration.jsp
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    /**
     * Получаем POST запрос с данными от пользователя и сохраняем их в БД, если они валидны
     */
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Создаём объект CreateUserDto
        CreateUserDto userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .image(req.getPart("image"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role("USER")
                .gender(req.getParameter("gender"))
                .build();


        try {
            // для начала с помощью userService создадим данного user (там идёт валидация и работа с dao)
            userService.create(userDto);

            // если валидация прошла успешно, то перенаправляем запрос на страничку с login
            resp.sendRedirect("/CoursedmdevServlet_war/login");

        } catch (ValidationException exception) {
            // иначе добавляем атрибут, чтобы потом вывести ошибки
            req.setAttribute("errors", exception.getErrors());

            // делаем опять запрос повторный на регистрацию
            doGet(req, resp);
        }
    }
}
