package deti.traveler.controller;

import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.service.TicketService;
import deti.traveler.service.TravelService;
import deti.traveler.service.utils.CURRENCY;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TravelController
{

    private final TravelService TravelService;
    private final TicketService TicketService;

    @GetMapping("/cities")
    public ResponseEntity<List<Travel>> getTravelsBetweenCities(@RequestBody TravelModel model, CURRENCY currency) throws IOException, InterruptedException {
        final List<Travel> result = TravelService.getTravel(model.getFromCity(), model.getToCity(), model.getDeparture(), model.getNumSeats(), currency);
        return !result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.FOUND) : new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTravel(@PathVariable Long id, String owner, int numSeatsBooked)
    {
        return new ResponseEntity<>(TicketService.purchaseTicket(id, owner,  numSeatsBooked), HttpStatus.CREATED);
    }
}