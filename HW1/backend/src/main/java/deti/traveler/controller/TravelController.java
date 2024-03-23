package deti.traveler.controller;

import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Travel;
import deti.traveler.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController
{

    private final TravelService service;

    @GetMapping("/cities")
    public List<Travel> getTravelsBetweenCities(@RequestBody TravelModel model)
    {
        return service.getTravel(model.getFromCity(), model.getToCity(), model.getDeparture(), model.getNumSeats());
    }
}
