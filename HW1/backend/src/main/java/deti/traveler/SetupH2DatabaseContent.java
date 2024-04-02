package deti.traveler;


import deti.traveler.entity.Travel;
import deti.traveler.service.TicketService;
import deti.traveler.service.TravelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SetupH2DatabaseContent
{
    @Autowired
    private TravelService service;

    public void setup() throws IOException, InterruptedException {
        Travel t = new Travel(null, "CityA", "CityB", LocalDateTime.of(2024, 3, 25, 10, 0),
                LocalDateTime.of(2024, 3, 26, 10, 0), 5, 100.0);

        service.addTravel(t);

    }



}
