package deti.traveler.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket
{
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("numSeats")
    private int numSeats;

    @JsonProperty("bookedAt")
    private LocalDateTime bookedAt;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("travel")
    private Travel travel;
}
