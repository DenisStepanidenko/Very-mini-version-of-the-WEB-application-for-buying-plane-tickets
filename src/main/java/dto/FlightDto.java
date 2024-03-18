package dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;




@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class FlightDto {
    private final Long id;
    private final String description;

}
