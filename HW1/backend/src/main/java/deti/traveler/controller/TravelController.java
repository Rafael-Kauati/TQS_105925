package deti.traveler.controller;

import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Travel;
import deti.traveler.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TravelController
{

    private final TravelService service;

    @GetMapping("/cities")
    public ResponseEntity<List<Travel>> getTravelsBetweenCities(@RequestBody TravelModel model) {
        final List<Travel> result = service.getTravel(model.getFromCity(), model.getToCity(), model.getDeparture(), model.getNumSeats());
        return !result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.FOUND) : new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}