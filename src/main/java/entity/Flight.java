package entity;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

@Value
@Builder
public class Flight {
    Long id;
    String flightNo;
    LocalDateTime departureDate;
    String departureAirportCode;
    LocalDateTime arrivalDate;
    String arrivalAirportCode;
    Integer aircraftId;
}
