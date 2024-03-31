package deti.traveler;

import deti.traveler.repository.TicketRepository;
import deti.traveler.repository.TravelRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class TravelerApplication {

		public static void main(String[] args) {
			ConfigurableApplicationContext context = SpringApplication.run(TravelerApplication.class, args);
			TravelRepository travelRepository = context.getBean(TravelRepository.class);
			TicketRepository ticketRepository = context.getBean(TicketRepository.class);
		}

}
