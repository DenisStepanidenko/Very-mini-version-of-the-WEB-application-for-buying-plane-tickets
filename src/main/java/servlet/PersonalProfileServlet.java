package servlet;

import dto.TicketDto;
import dto.TicketDtoForPerson;
import dto.UserDto;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TicketService;
import util.JspHelper;
import util.PropertiesUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

/**
 * Сервлет для личной странички каждого пользователя
 */
@WebServlet("/personalProfile")
public class PersonalProfileServlet extends HttpServlet {
    private final String basePath = PropertiesUtil.get("image.base.url");
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // для начала нужно сохранить путь до фотографии данного пользователя, чтобы потом с помощью imageServlet подгрузить его на страницу
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setAttribute("pathToPhoto", userDto.getImage());

        // теперь нужно посмотреть, какие билеты есть у данного пользователя, чтобы их далее можно было вывести
        // структура ticketDtoForPerson
        // seatNo - Description of flight. Ещё имеется flightId
        List<TicketDtoForPerson> allTickets = ticketService.findAllByName(userDto.getName());
        req.setAttribute("allTickets", allTickets);

        req.getRequestDispatcher(JspHelper.getPath("personalProfile")).forward(req, resp);
    }
}
