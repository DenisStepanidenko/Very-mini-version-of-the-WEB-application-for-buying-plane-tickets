package dao;

import entity.Flight;
import entity.FlightStatus;
import lombok.SneakyThrows;
import util.ConnectionManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    public static final FlightDao INSTANCE = new FlightDao();



    private FlightDao() {
    }

    private static final String FIND_ALL = """
               SELECT *
               FROM flight
            """;

    private static final String FIND_FLIGHT_BY_ID = "SELECT * FROM flight where id = ?";
    private static final String FIND_COUNTRY_BY_AIRPORT_CODE = "SELECT * FROM airport where code = ?";
    private static final String FIND_AIRCRAFT_MODEL_BY_ID = "SELECT * FROM aircraft WHERE id = ?";

    public static FlightDao getInstance() {
        return INSTANCE;
    }


    /**
     * Метод, который возвращает все полёты
     */
    @Override
    public List<Flight> findAll() {
        var connection = ConnectionManager.get();
        try {
            var preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Метод, который из текущего ResultSet строит объект типа Flight
     */
    private Flight buildFlight(ResultSet resultSet) {
        try {
            return Flight.builder()
                    .id(resultSet.getObject("id", Long.class))
                    .flightNo(resultSet.getObject("flight_no", String.class))
                    .departureDate(resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime())
                    .departureAirportCode(resultSet.getObject("departure_airport_code", String.class))
                    .arrivalDate(resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime())
                    .arrivalAirportCode(resultSet.getObject("arrival_airport_code", String.class))
                    .aircraftId(resultSet.getObject("aircraft_id", Integer.class))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Метод, который по id полёта возвращает всю информацию о нём
     */
    @SneakyThrows
    @Override
    public Optional<Flight> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_FLIGHT_BY_ID)) {
            preparedStatement.setLong(1, id);

            Flight flight = null;
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flight = buildFlight(resultSet);
            }
            return Optional.ofNullable(flight);
        }

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }


    @Override
    public Flight save(Flight entity) {
        return null;
    }


    /**
     * Данный метод возвратит описание рейса в виде время вылета,Аэропорт - время прилёта,Аэропорт
     */
    @SneakyThrows
    public String getDescriptionByFlightId(Long flightId) {
        // данный метод не вернёт null, так как мы его используем когда уже нашли билет пассажира
        // а значит данный рейс точно есть
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_FLIGHT_BY_ID)) {
            preparedStatement.setLong(1, flightId);

            // так как id - уникальное и мы гарантируем, что оно есть - вернётся одна строчка в resultSet
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String departureDate = String.valueOf(resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime());
            String departureAirportCode = resultSet.getObject("departure_airport_code", String.class);
            String arrivalDate = String.valueOf(resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime());
            String arrivalAirportCode = resultSet.getObject("arrival_airport_code", String.class);


            return departureDate + "," + departureAirportCode + " - " + arrivalDate + "," + arrivalAirportCode;

        }

    }


    /**
     * Метод, который возвратит город по коду аэропорта
     */
    @SneakyThrows
    public String findCountryByAirportCode(String departureAirportCode) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COUNTRY_BY_AIRPORT_CODE)) {
            preparedStatement.setString(1, departureAirportCode);

            // так как id - уникальное и мы гарантируем, что оно есть - вернётся одна строчка в resultSet
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();


            return resultSet.getObject("city", String.class);

        }
    }


    /**
     * Метод, который возвращает модель самолёта
     */
    @SneakyThrows
    public String findAircraftModel(Integer aircraftId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_AIRCRAFT_MODEL_BY_ID)) {
            preparedStatement.setInt(1, aircraftId);

            // так как id - уникальное и мы гарантируем, что оно есть - вернётся одна строчка в resultSet
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();


            return resultSet.getObject("model", String.class);

        }
    }
}
