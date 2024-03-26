package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

/**
 * Данный сервлет занимается тем, что удаляет текущего user из сессии и возвращает на страничку логина
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Данный метод удаляет пользователя из Session и делает редирект на страничку логина
     */
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // тут происходит собственно сама инвалидация user
        req.getSession().invalidate();

        // редирект на страничку с логином и почтой
        resp.sendRedirect("/CoursedmdevServlet_war/login");
    }
}
