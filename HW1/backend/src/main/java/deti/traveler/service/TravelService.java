package deti.traveler.service;

import deti.traveler.cache.TTLCurrencyCache;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.repository.TicketRepository;
import deti.traveler.repository.TravelRepository;
import deti.traveler.service.utils.CURRENCY;
import deti.traveler.service.utils.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelService
{

    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final TravelRepository travelRepository;

    private TTLCurrencyCache converter = null;

    public List<Travel> getTravel(final String from, final String to, final LocalDateTime departure, final int numSeats, CURRENCY currency) throws IOException, InterruptedException {
        if (converter == null)
        {
            converter = new TTLCurrencyCache(new CurrencyConverter());
        }
        log.info("\nFetching a travel : "+ from+" -> "+to+", with atleast "+numSeats+" avaible | to pay in "+currency);
        List<Travel> travelsFound =  travelRepository.findByFromCityAndToCityAndDepartureAndNumSeatsIsGreaterThanEqual(from, to, departure, numSeats);

        if(!travelsFound.isEmpty()) {log.info("\nFound : \n"+travelsFound);}

        for(Travel t : travelsFound)
        {
            t.setPrice(converter.convertValue(currency, t.getPrice()));
        }

        return travelsFound;
    }

    public Ticket purchaseTicket(final Long id, String owner, int numSeatsBooked) {
        final Optional<Travel> travel = travelRepository.findById(id);
        log.info("\nTravel fetched : " + travel.get().toString() + " with id : " + id);
        final Ticket ticket = new Ticket().builder()
                .purchasedAt(LocalDateTime.now())
                .travel(travel.get())
                .owner(owner)
                .build();
        log.info("\nTicket booked by : " + owner + ", num of seats booked : " + numSeatsBooked);
        ticketRepository.save(ticket);
        travelRepository.updateTraveSeatslById(id, numSeatsBooked); // Corrected method name
        return ticket;
    }

}
