package deti.traveler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelTicketDTO {
    private Long id;
    private String owner;
    private LocalDateTime purchasedAt;
    private Long travelId;
    private LocalDate arrive;
    private LocalDate departure;
    private String fromCity;
    private int numSeats;
    private Double price;
    private String toCity;

}
