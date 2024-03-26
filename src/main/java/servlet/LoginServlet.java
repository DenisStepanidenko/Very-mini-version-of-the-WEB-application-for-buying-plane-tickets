package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.UserService;
import util.JspHelper;

import java.io.IOException;


/**
 * Данный сервлет занимается обработкой login странички
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * Зависимость для работы с UserService
     */
    private final UserService userService = UserService.getInstance();

    /**
     * Данный метод ловит get запрос на страничку логина, и перенаправляет на jsp страничку
     */
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }


    /**
     * Данный метод проверят введённые пользователем данные для входа, и если всё успешно - загружает данного пользователя в Session, если нет - возвращает страничку с логином
     */
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        // получаем параметры для аутентификации пользователя
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        // собственно сама проверка и решение куда перенаправлять пользователя
        System.out.println(userService.login(email, password));
        userService.login(email, password)
                .ifPresentOrElse(
                        user -> onLoginSucess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }


    /**
     * В случае ошибки при аутентификации перенаправляем пользователя опять на страничку логина
     */
    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/CoursedmdevServlet_war/login?error");
    }

    /**
     * В случае успеха перенаправляем пользователя в личный кабинет
     */
    @SneakyThrows
    private void onLoginSucess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        // также позаботимся о сохранении в сессию пользователя, чтобы потом отобразить информацию только для него
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/CoursedmdevServlet_war/personalProfile");
    }
}
