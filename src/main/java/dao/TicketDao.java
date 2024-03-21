package dao;

import entity.Ticket;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {
    private static final String FIND_ALL_BY_FLIGHT_ID = """
            SELECT *
            FROM ticket
            WHERE flight_id = ?
            """;

    /**
     * SQL запрос для поиска билетов конкретного пользователя
     */
    private static final String FIND_ALL_BY_NAME_OF_PERSON = "SELECT * FROM ticket WHERE passenger_name = ?";
    private static final TicketDao INSTANCE = new TicketDao();
    private static final String SAVE_TICKET = "INSERT INTO ticket(passenger_name, flight_id, seat_no) values (?,?,?)";

    private TicketDao() {
    }

    /**
     * Данный метод ищет все билеты по id полёта
     */
    public List<Ticket> findAllByFlightId(Long flightId) {
        try (Connection connection = ConnectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID);
            preparedStatement.setObject(1, flightId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Метод, который из текущего resultSet создаёт объект Ticker
     */
    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return Ticket.builder()
                .id(resultSet.getObject("id", Long.class))
                .passengerName(resultSet.getObject("passenger_name", String.class))
                .flightId(resultSet.getObject("flight_id", Long.class))
                .seatNo(resultSet.getObject("seat_no", String.class))
                .build();
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }


    /**
     * Данный метод ищет все билеты пользователя с именем name (пока что в БД имя уникальное)
     */
    @SneakyThrows
    public List<Ticket> findAllByNameOfPerson(String name) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_NAME_OF_PERSON)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }

            return tickets;
        }
    }

    /**
     * Метод, который сохраняет билет
     */
    @SneakyThrows
    public void saveTicket(Long id, String seatNo, String name) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_TICKET)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.setString(3, seatNo);

            preparedStatement.executeUpdate();

        }
    }
}
