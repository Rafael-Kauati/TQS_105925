package deti.traveler.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Travel")
@Getter
@Setter
public class Travel
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String fromCity;

    @Column
    private String toCity;

    @Column
    private LocalDateTime departure;

    @Column
    private LocalDateTime arrive;

    @Column

    private int numSeats;
    @Column

    private Double price;


}
