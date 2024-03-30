package deti.traveler.service;

import deti.traveler.cache.TTLCurrencyCache;
import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.repository.TicketRepository;
import deti.traveler.repository.TravelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TicketService
{
    private TicketRepository ticketRepository;
    private TravelRepository travelRepository;
    private TTLCurrencyCache currencyCache;

    public Ticket purchaseTicket(final Long id, String owner, int numSeatsBooked)
    {

        final Optional<Travel> travel = travelRepository.findById(id);
        log.info("\nTravel fetched : "+travel.get().toString()+ " with id : "+id);
        final Ticket ticket = new Ticket().builder()
                .purchasedAt(LocalDateTime.now())
                .travel(travel.get())
                .owner(owner)
                .build();
        log.info("\nTicked booked by : "+owner+ ", num of seats booked : "+numSeatsBooked);
        ticketRepository.save(ticket);
        travelRepository.updateTraveSeatslById(id, numSeatsBooked);
        return ticket;
    }
}
