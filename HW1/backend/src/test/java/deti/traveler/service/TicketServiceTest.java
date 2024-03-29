package deti.traveler.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.repository.TicketRepository;
import deti.traveler.repository.TravelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TravelRepository travelRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testPurchaseTicket() {
        // Mock data
        Long travelId = 1L;
        String owner = "John Doe";
        LocalDateTime now = LocalDateTime.now();
        Travel travel = new Travel();
        travel.setId(travelId);
        travel.setNumSeats(10); // Assuming there are 10 seats available
        Optional<Travel> optionalTravel = Optional.of(travel);

        // Mock behavior
        when(travelRepository.findById(travelId)).thenReturn(optionalTravel);

        // Method call
        Ticket purchasedTicket = ticketService.purchaseTicket(travelId, owner, 1);

        // Verification
        assertNotNull(purchasedTicket);
        assertEquals(owner, purchasedTicket.getOwner());
        assertNotNull(purchasedTicket.getPurchasedAt());
        assertEquals(travel, purchasedTicket.getTravel()); // Assuming Ticket has appropriate equals() method

        // Verify that save method is called on ticketRepository
        verify(ticketRepository, times(1)).save(purchasedTicket);

        // Verify that updateTravelSeatsById method is called on travelRepository
        verify(travelRepository, times(1)).updateTraveSeatslById(travelId,  1);
    }
}
