package deti.traveler.service;

import deti.traveler.entity.Travel;
import deti.traveler.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService
{

    private final TravelRepository repository;

    public List<Travel> getTravel(final String from, final String to, final LocalDateTime departure, final int numSeats)
    {
        return repository.findByFromCityAndToCityAndDepartureAndNumSeatsIsGreaterThanEqual(from, to, departure, numSeats);
    }

}
