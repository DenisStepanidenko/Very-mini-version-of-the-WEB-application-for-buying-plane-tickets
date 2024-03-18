package servlet;

import dto.CreateUserDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.UserService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;


@WebServlet("/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();


    /**
     * Получаем GET запрос на регистрацию
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("gender", List.of("MALE", "FEMALE"));

        // Здесь перенаправляем полностью запрос с помощью forward на registration.jsp
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    /**
     * Получаем POST запрос с данными от пользователя
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            userService.create(userDto);
            resp.sendRedirect("/CoursedmdevServlet_war/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
