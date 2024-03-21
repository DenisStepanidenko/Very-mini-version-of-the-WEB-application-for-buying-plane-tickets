package entity;

import lombok.Builder;
import lombok.Value;


/**
 * Сущность ticket - совпадает с описанием из БД
 */
@Value
@Builder
public class Ticket {
    Long id;
    String passengerName;
    Long flightId;
    String seatNo;
}
