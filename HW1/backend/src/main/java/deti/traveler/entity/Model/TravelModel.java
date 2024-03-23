package deti.traveler.entity.Model;

import jakarta.json.bind.annotation.JsonbTypeSerializer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class TravelModel
{
    private final String fromCity, toCity;
    private final double price;
    private LocalDateTime departure;
    private final int numSeats;

}
