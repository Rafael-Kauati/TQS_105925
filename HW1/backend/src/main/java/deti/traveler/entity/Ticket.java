package deti.traveler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchasedAt")
    private LocalDateTime purchasedAt;

    @Column(name = "owner")
    private String owner;

    @Column(name = "numOfSeats")  // corrected field name
    private int numOfSeats;  // corrected field name

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;
}
