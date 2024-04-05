package deti.traveler.repository;

import deti.traveler.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("TicketRepository")
public interface TicketRepository extends JpaRepository<Ticket, Long>
{}
