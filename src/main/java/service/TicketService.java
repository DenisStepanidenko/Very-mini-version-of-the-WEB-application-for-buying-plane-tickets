package service;

import dao.TicketDao;
import dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TicketService {
    public static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    public static TicketService getInstance(){
        return INSTANCE;
    }
    public List<TicketDto> findAllByFindFlightId(Long flightId){
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(x -> new TicketDto(x.getId() , x.getFlightId() , x.getSeatNo()))
                .collect(toList());
    }

    private TicketService(){}
}
