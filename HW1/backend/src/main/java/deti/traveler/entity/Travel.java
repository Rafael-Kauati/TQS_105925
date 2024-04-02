package deti.traveler.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Travel")
@Table(name = "Travel")
@Getter
@Setter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fromcity")
    private String fromcity;

    @Column(name = "tocity")
    private String tocity;

    @Column(name = "departure")
    private LocalDateTime departure;

    @Column(name = "arrive")
    private LocalDateTime arrive;

    @Column(name = "numseats")  // Remove the space here
    private int numseats;

    @Column(name = "price")
    private Double price;

}
