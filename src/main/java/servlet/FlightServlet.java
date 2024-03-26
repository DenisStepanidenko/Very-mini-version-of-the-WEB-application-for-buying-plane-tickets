package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.FlightService;
import util.JspHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/**
 * Данный сервлет будет заниматься обработкой показа всех рейсов пользователю
 */
@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();


    /**
     * Данный метод возвращает страничку пользователю, на которой перечислены все полёты в виде краткого описания
     */
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // для начала добавим все авиарейсы в атрибут
        req.setAttribute("flights", flightService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("flights"))
                .forward(req, resp);

    }


}
