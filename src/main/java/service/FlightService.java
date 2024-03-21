package service;

import dao.FlightDao;
import dto.FlightDto;
import dto.FlightDtoForFullDescription;
import entity.Flight;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Слой сервисов, который занимается бизнес логикой, связанной с полётами
 */
public class FlightService {
    /**
     * Реализация singleton
     */
    private static final FlightService INSTANCE = new FlightService();
    /**
     * Зависимость для работы с FlightDao
     */
    private final FlightDao flightDao = FlightDao.INSTANCE;


    /**
     * Метод, который возвращает все описания полётов
     */
    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(flight.getId(), """
                        %s,%s - %s,%s
                        """.formatted(flight.getDepartureDate(), flight.getDepartureAirportCode(), flight.getArrivalDate(), flight.getArrivalAirportCode())))
                .collect(toList());
    }

    public FlightDtoForFullDescription findFlightById(Long id) {
        // получили объект полёт
        Flight flight = flightDao.findById(id).get();

        // теперь нужно получить страну из которой мы вылетаем
        String departureCity = flightDao.findCountryByAirportCode(flight.getDepartureAirportCode());

        // теперь нужно получить страну в которую мы прилетаем
        String arrivalCity = flightDao.findCountryByAirportCode(flight.getArrivalAirportCode());

        // теперь нужно получить модель самолёта, на котором мы летим
        String modelOfAircraft = flightDao.findAircraftModel(flight.getAircraftId());


        // возвращаем готовый объект
        return FlightDtoForFullDescription.builder()
                .id(flight.getId())
                .departureDate(String.valueOf(flight.getDepartureDate()))
                .departureAirportCode(departureCity)
                .arrivalDate(String.valueOf(flight.getArrivalDate()))
                .arrivalAirportCode(arrivalCity)
                .modelOfAircraft(modelOfAircraft)
                .build();
    }

    /**
     * Реализация singleton
     */
    public static FlightService getInstance() {
        return INSTANCE;
    }

    /**
     * Реализация singleton
     */
    private FlightService() {
    }
}
