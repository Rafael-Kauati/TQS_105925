package deti.traveler.repository;

import deti.traveler.entity.Ticket;
import deti.traveler.entity.TravelTicketDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new deti.traveler.entity.TravelTicketDTO(t.id, t.owner, t.purchasedAt, t.travel.id, t.travel.arrive, t.travel.departure, t.travel.fromcity, t.travel.numseats, t.travel.price, t.travel.tocity) FROM Ticket t JOIN t.travel WHERE t.owner = :owner")
    List<TravelTicketDTO> findTicketsWithTravelInfoByOwner(@Param("owner") String owner);
}