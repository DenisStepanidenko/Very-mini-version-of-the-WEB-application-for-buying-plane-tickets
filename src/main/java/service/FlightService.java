package service;

import dao.FlightDao;
import dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.INSTANCE;

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(flight.getId(), """
                        %s-%s-%s
                        """.formatted(flight.getDepartureAirportCode() , flight.getArrivalAirportCode() , flight.getStatus())))
                .collect(toList());
    }
    public static FlightService getInstance(){
        return INSTANCE;
    }

    private FlightService(){}
}
