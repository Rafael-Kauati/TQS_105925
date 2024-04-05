package deti.traveler;


import deti.traveler.entity.Travel;
import deti.traveler.service.TicketService;
import deti.traveler.service.TravelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SetupH2DatabaseContent
{
    @Autowired
    private TravelService service;

    public void setup() throws IOException, InterruptedException {
        Travel t1 = new Travel(null, "CityA", "CityB", LocalDate.of(2024, 3, 25),
                LocalDate.of(2024, 3, 26), 20, 15.0);

        Travel t2 = new Travel(null, "CityB", "CityA", LocalDate.of(2024, 3, 25),
                LocalDate.of(2024, 3, 25), 11, 25.0);

        Travel t3 = new Travel(null, "CityC", "CityD", LocalDate.of(2024, 3, 25),
                LocalDate.of(2024, 3,20), 20, 10.0);

        service.addTravel(t1); service.addTravel(t2); service.addTravel(t3);

    }



}
