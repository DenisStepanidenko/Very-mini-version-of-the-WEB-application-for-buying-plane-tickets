package servlet;

import dto.FlightDtoForFullDescription;
import dto.TicketDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.FlightService;
import service.TicketService;
import util.JspHelper;


import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 * Данный сервлет занимается обработкой билетов на рейсы
 */
@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    /**
     * Зависимость для TicketService
     */
    private final TicketService ticketService = TicketService.getInstance();
    /**
     * Зависимость для FlightService
     */
    private final FlightService flightService = FlightService.getInstance();


    /**
     * Данный метод возвращает страничку, где есть описание рейса и всевозможные билеты на него
     */
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // сначала достанем из параметров id полёта
        Long flightId = Long.valueOf(req.getParameter("flightId"));


        // для начала нужно создать полное описание рейса
        FlightDtoForFullDescription flightDtoForFullDescription = flightService.findFlightById(flightId);

        req.setAttribute("flightDto", flightDtoForFullDescription);


        // Но теперь нужен список билетов уже купленных на этот рейс
        List<TicketDto> boughtTickets = ticketService.findAllByFindFlightId(flightId);
        req.setAttribute("boughtTickets", boughtTickets);

        // Теперь нужен список всех билетов, которые остались для покупки
        List<TicketDto> freeTickets = ticketService.getFreeSeatNo(boughtTickets, flightId);
        req.setAttribute("freeTickets", freeTickets);

        req.getRequestDispatcher(JspHelper.getPath("tickets")).forward(req, resp);


    }
}
