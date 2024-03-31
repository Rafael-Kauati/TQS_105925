package deti.traveler.repository;

import deti.traveler.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("TicketRepository")
public interface TicketRepository extends CrudRepository<Ticket, Long>
{}
