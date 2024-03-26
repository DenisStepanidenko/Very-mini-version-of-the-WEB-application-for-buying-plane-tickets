package servlet;

import dto.FlightDtoForFullDescription;
import dto.UserDto;
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

/**
 * Данный сервлет предназначен для обработки удаления билетов
 */
@WebServlet("/deleteTicket")
public class DeleteTicket extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();
    private final TicketService ticketService = TicketService.getInstance();

    /**
     * Данный метод будет возвращать страничку пользователю, где будет информация о билете, а также возможность его удалить
     */
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Long flightId = Long.valueOf(req.getParameter("flightId"));
        String seatNo = req.getParameter("seatNo");
        FlightDtoForFullDescription flightDtoForFullDescription = flightService.findFlightById(flightId);
        req.setAttribute("flightDto", flightDtoForFullDescription);

        req.setAttribute("seatNo", seatNo);
        req.getRequestDispatcher(JspHelper.getPath("deleteTicket")).forward(req, resp);
    }

    /**
     * Данный метод удаляет билет пользователя
     */
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long flightId = Long.valueOf(req.getParameter("flightId"));
        String seatNo = req.getParameter("seatNo");
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        String nameOfPerson = user.getName();

        ticketService.deleteTicket(flightId, seatNo, nameOfPerson);
        resp.sendRedirect("/CoursedmdevServlet_war/personalProfile");
    }
}
