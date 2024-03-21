package service;

import dao.FlightDao;
import dao.TicketDao;
import dto.TicketDto;
import dto.TicketDtoForPerson;
import entity.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


public class TicketService {

    /**
     * Множество, в котором хранится всевозможные места
     */
    private static final Set<String> possibleTickets = Set.of("A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2");

    /**
     * Зависимость для FlightDao
     */
    private static final FlightDao flightDao = FlightDao.getInstance();

    /**
     * Реализация singleton
     */
    private static final TicketService INSTANCE = new TicketService();

    /**
     * Зависимость для TicketDao
     */
    private final TicketDao ticketDao = TicketDao.getInstance();

    /**
     * Реализация singleton
     */
    public static TicketService getInstance() {
        return INSTANCE;
    }

    /**
     * Метод, который возвращает описание всех билетов по id полёта
     */
    public List<TicketDto> findAllByFindFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(x -> TicketDto.builder()
                        .flightId(flightId)
                        .seatNo(x.getSeatNo())
                        .build())
                .collect(toList());
    }

    /**
     * Реализация singleton
     */
    private TicketService() {
    }


    /**
     * Данный метод должен выдать все билеты пользователя с именем name (пока что в БД имя уникальное)
     */
    public List<TicketDtoForPerson> findAllByName(String name) {
        // для начала получаем ticket
        List<Ticket> allTicketsForPerson = ticketDao.findAllByNameOfPerson(name);

        // теперь основная задача это получить описание полёта по flightId, которое хранится в ticket
        List<TicketDtoForPerson> ticketDtoForPersonList = new ArrayList<>();

        // пройдёмся по каждому ticket и запросим у flightId описание
        for (Ticket ticket : allTicketsForPerson) {
            ticketDtoForPersonList.add(TicketDtoForPerson.builder()
                    .seatNo(ticket.getSeatNo())
                    .description(flightDao.getDescriptionByFlightId(ticket.getFlightId())).build());
        }

        return ticketDtoForPersonList;

    }


    /**
     * Возвращает свободные места, зная какие места уже заняты
     */
    public List<TicketDto> getFreeSeatNo(List<TicketDto> boughtTickets, Long flightId) {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (String seatNo : possibleTickets) {
            if (isCheck(seatNo, boughtTickets)) {
                // сюда мы попали, если это место не занято
                ticketDtoList.add(TicketDto.builder()
                        .flightId(flightId)
                        .seatNo(seatNo)
                        .build());
            }
        }

        return ticketDtoList;
    }

    /**
     * Метод, который проверяет есть ли seatNo среди boughtTickets
     */
    private boolean isCheck(String seatNo, List<TicketDto> boughtTickets) {
        for (TicketDto ticketDto : boughtTickets) {
            if (seatNo.equals(ticketDto.getSeatNo())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Метод, который сохраняет билет
     */
    public void saveTicket(Long id, String seatNo, String name) {
        ticketDao.saveTicket(id, seatNo, name);
    }
}
