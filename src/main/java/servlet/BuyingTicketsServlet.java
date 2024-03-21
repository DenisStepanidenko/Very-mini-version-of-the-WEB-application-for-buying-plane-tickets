package servlet;

import dto.FlightDtoForFullDescription;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FlightService;
import service.TicketService;
import util.JspHelper;

import java.io.IOException;

/**
 * Данный сервлет будет заниматься обработкой покупки билетов
 */
@WebServlet("/buyingTicket")
public class BuyingTicketsServlet extends HttpServlet {
    /**
     * Зависимость для FlightService
     */
    private final FlightService flightService = FlightService.getInstance();

    /**
     * Зависимость для TicketService
     */
    private final TicketService ticketService = TicketService.getInstance();

    /**
     * Будем показывать страничку пользователю, где ещё раз напишем информацию про рейс и добавим кнопочку купить билет
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.valueOf(req.getParameter("flightId"));

        // для начала нужно создать полное описание рейса
        FlightDtoForFullDescription flightDtoForFullDescription = flightService.findFlightById(flightId);
        req.setAttribute("flightDto", flightDtoForFullDescription);

        req.setAttribute("seatNo", req.getParameter("seatNo"));

        req.getRequestDispatcher(JspHelper.getPath("buyTicket")).forward(req, resp);
    }


    /**
     * Данный метод будет записывать билет пользователю
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("flightId"));
        String seatNo = req.getParameter("seatNo");
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        // сохраняем билет в БД
        ticketService.saveTicket(id, seatNo, userDto.getName());

        resp.sendRedirect("/CoursedmdevServlet_war/personalProfile");
    }
}
