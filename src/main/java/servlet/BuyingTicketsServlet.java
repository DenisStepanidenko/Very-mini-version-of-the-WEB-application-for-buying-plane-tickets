package servlet;

import dto.FlightDtoForFullDescription;
import dto.UserDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.FlightService;
import service.TicketService;
import util.JspHelper;


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
     * Данный метод возвращает страничку пользователю, где будет храниться полная информация о рейсе и возможность его купить
     */
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Long flightId = Long.valueOf(req.getParameter("flightId"));

        // для начала нужно создать полное описание рейса
        FlightDtoForFullDescription flightDtoForFullDescription = flightService.findFlightById(flightId);
        req.setAttribute("flightDto", flightDtoForFullDescription);

        req.setAttribute("seatNo", req.getParameter("seatNo"));

        req.getRequestDispatcher(JspHelper.getPath("buyTicket")).forward(req, resp);
    }


    /**
     * Данный метод будет записывать билет пользователя в БД и возвращаться на страничку с личным кабинетом
     */
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.valueOf(req.getParameter("flightId"));
        String seatNo = req.getParameter("seatNo");
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        // сохраняем билет в БД
        ticketService.saveTicket(id, seatNo, userDto.getName());

        resp.sendRedirect("/CoursedmdevServlet_war/personalProfile");
    }
}
