package deti.traveler.service;

import deti.traveler.entity.Travel;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelService
{

    private final TravelRepository repository;
    private  CurrencyConverter converter = null;

    public List<Travel> getTravel(final String from, final String to, final LocalDateTime departure, final int numSeats, CURRENCY currency) throws IOException, InterruptedException {
        if (converter == null)
        {
            converter = new CurrencyConverter();
        }
        log.info("\nFetching a travel : "+ from+" -> "+to+", with atleast "+numSeats+" avaible | to pay in "+currency);
        List<Travel> travelsFound =  repository.findByFromCityAndToCityAndDepartureAndNumSeatsIsGreaterThanEqual(from, to, departure, numSeats);

        if(!travelsFound.isEmpty()) {log.info("\nFound : \n"+travelsFound);}

        for(Travel t : travelsFound)
        {
            t.setPrice(converter.convert(currency, t.getPrice()));
        }

        return travelsFound;
    }

}
