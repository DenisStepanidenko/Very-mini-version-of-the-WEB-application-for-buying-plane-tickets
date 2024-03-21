package dto;

import lombok.Builder;
import lombok.Value;


/**
 * Класс, который используется для показа полного описания рейса на страничке данного рейса
 */
@Value
@Builder
public class FlightDtoForFullDescription {
    Long id;
    String departureDate;
    String departureAirportCode;
    String arrivalDate;
    String arrivalAirportCode;
    String modelOfAircraft;
}
