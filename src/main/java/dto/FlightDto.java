package dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Класс для отображения информации пользователю о билетах
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class FlightDto {
    private final Long id;

    // описание полёта в виде дата вылета,Аэропорт - дата прилёта, Аэропорт
    private final String description;

}
