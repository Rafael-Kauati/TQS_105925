package deti.traveler;

import deti.traveler.entity.Travel;
import deti.traveler.repository.TicketRepository;
import deti.traveler.repository.TravelRepository;
import deti.traveler.service.TravelService;
import deti.traveler.service.utils.CURRENCY;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class TravelerApplication {

		@Autowired
		private TravelService service;

		public static void main(String[] args) {

			SpringApplication.run(TravelerApplication.class, args);


		}


		@Bean
		CommandLineRunner commandLineRunner(TravelRepository repository)
		{

			return args -> {
				Travel  t = new Travel(null, "CityA", "CityB", LocalDateTime.of(2024, 3, 25, 10, 0),
						LocalDateTime.of(2024, 3, 26, 10, 0), 5, 100.0);
				repository.save(t);

				log.info("\nSearching for the only travel : "+service.getTravel(t.getFromcity(), t.getTocity(), t.getDeparture(), t.getNumseats(), CURRENCY.EUR).getFirst().getNumseats());


				t.setNumseats(t.getNumseats() - 2);
				repository.save(t);

				log.info("\nSearching for the only travel again : "+service.getTravel(t.getFromcity(), t.getTocity(), t.getDeparture(), t.getNumseats(), CURRENCY.EUR).getFirst().getNumseats());



			};
		}

}
