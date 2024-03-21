package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FlightService;
import util.JspHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/**
 * Данный сервлет принимает запросы на показ всех рейсов
 */
@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();


    /**
     Принимаем GET запрос на показ всех рейсов
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        // для начала добавим все авиарейсы в атрибут
        req.setAttribute("flights", flightService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("flights"))
                .forward(req, resp);

    }


}
