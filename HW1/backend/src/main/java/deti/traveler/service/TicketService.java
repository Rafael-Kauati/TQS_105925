package deti.traveler.service;

import deti.traveler.cache.TTLCurrencyCache;
import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.repository.TicketRepository;
import deti.traveler.repository.TravelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService
{
    private TicketRepository ticketRepository;
    private TravelRepository travelRepository;
    private TTLCurrencyCache currencyCache;

    public Ticket purchaseTicket(final Long id, String owner)
    {
        final Optional<Travel> travel = travelRepository.findById(id);
        final Ticket ticket = new Ticket().builder()
                .purchasedAt(LocalDateTime.now())
                .travel(travel.get())
                .owner(owner)
                .build();

        ticketRepository.save(ticket);
        travelRepository.updateTraveSeatslById(id, travel.get().getNumSeats());
        return ticket;
    }
}
