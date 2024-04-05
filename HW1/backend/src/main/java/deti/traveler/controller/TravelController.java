package deti.traveler.controller;

import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.service.TicketService;
import deti.traveler.service.TravelService;
import deti.traveler.service.utils.CURRENCY;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
public class TravelController {
    @Autowired
    private  TravelService travelService;


    //Option deixar de usar rquest body e usar apenas RquestParam
    @GetMapping("/cities/{currency}")
    public ResponseEntity<List<Travel>> getTravelsBetweenCities(
            @RequestParam("fromCity") String fromCity,
            @RequestParam("toCity") String toCity,
            @RequestParam("departure") LocalDate departure,
            @RequestParam("numSeats") int numSeats,
            @PathVariable CURRENCY currency) throws IOException, InterruptedException {

        final List<Travel> result = travelService.getTravel(fromCity, toCity, departure, numSeats, currency);
        return !result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.FOUND) : new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/purchase/{id}")
    public ResponseEntity<Ticket> purchaseTravel(@PathVariable Long id,@RequestParam("owner") String owner,@RequestParam("numSeatsBooked") int numSeatsBooked) {
        return new ResponseEntity<>(travelService.purchaseTicket(id, owner, numSeatsBooked), HttpStatus.CREATED);
    }
}