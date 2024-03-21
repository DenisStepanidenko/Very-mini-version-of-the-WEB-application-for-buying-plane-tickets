package dto;

import lombok.Builder;
import lombok.Value;


/**
 * Класс предназначается для отображение в личной кабинете пользователя информации о его билетах
 */
@Value
@Builder
public class TicketDtoForPerson {
    /**
     * Номер места
     */
    String seatNo;
    /**
     * Описание полёта в виде строки дата вылета,Аэропорт - дата прилёта, Аэропорт
     */
    String description;
}
