package dto;


import lombok.Builder;
import lombok.Value;


/**
 * Класс для отображения билета пользователю
 */
@Value
@Builder
public class TicketDto {
    Long flightId;
    String seatNo;

}
