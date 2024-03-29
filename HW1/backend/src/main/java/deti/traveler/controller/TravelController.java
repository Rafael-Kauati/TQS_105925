package deti.traveler.controller;

import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.service.TicketService;
import deti.traveler.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TravelController
{

    private final TravelService TravelService;
    private final TicketService TicketService;

    @GetMapping("/cities")
    public ResponseEntity<List<Travel>> getTravelsBetweenCities(@RequestBody TravelModel model) {
        final List<Travel> result = TravelService.getTravel(model.getFromCity(), model.getToCity(), model.getDeparture(), model.getNumSeats());
        return !result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.FOUND) : new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTravel(@PathVariable Long id, String owner)
    {
        return new ResponseEntity<>(TicketService.purchaseTicket(id, owner), HttpStatus.CREATED);
    }
}