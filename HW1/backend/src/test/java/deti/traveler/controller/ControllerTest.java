package deti.traveler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import deti.traveler.entity.Model.TravelModel;
import deti.traveler.entity.Ticket;
import deti.traveler.entity.Travel;
import deti.traveler.service.TravelService;
import deti.traveler.service.utils.CURRENCY;
import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

//@WebMvcTest(controllers = TravelController.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
class ControllerTest
{
    @Autowired
    private MockMvc mockController;

    @MockBean
    private TravelService service;

    private final Travel DummyTravel = new Travel(1L, "Dublin, Ireland","Galway, Ireland",LocalDate.now(),  LocalDate.now(), 6, 11.99);

    private final Ticket DummyTicket = Ticket.builder()
            .owner("James Lee")
            .travel(DummyTravel)
            .purchasedAt(LocalDateTime.now())
            .build();


    @Test
    void testSearchByGivenCities() throws Exception {
        // Create a travel model JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String travelModelJson = objectMapper.writeValueAsString(new TravelModel("Dublin, Ireland", "Galway, Ireland", LocalDate.now(), 6));

        // Perform the GET request to the controller method
        mockController.perform(get("/cities/EUR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("fromCity", "Dublin, Ireland")
                        .param("toCity", "Galway, Ireland")
                        .param("departure", LocalDate.now().toString())
                        .param("numSeats", "6")
                )
                .andDo(print()) // Print the response for debugging if needed
                .andExpect(status().isFound()); // Expecting a 302 Found status
    }


    @Test
    void TestPurchaseTicketForATravel() throws Exception {

        //'http://localhost:9090/purchase/1?owner=JohnDoe&numSeatsBooked=2'

        mockController.perform(get("/purchase/1")
                        .param("owner", "JohnDoe")
                        .param("numSeatsBooked", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }




    @BeforeEach
    void setupTest() throws IOException, InterruptedException {
        final List<Travel> trips = new ArrayList<>();
        trips.add(DummyTravel);

        when(service.purchaseTicket(
                eq(DummyTravel.getId()),
                eq(DummyTicket.getOwner()),
                anyInt()
        )).thenReturn(DummyTicket);

        when(service.getTravel(
                eq(DummyTravel.getFromcity()),
                eq(DummyTravel.getTocity()),
                any(LocalDate.class),
                eq(DummyTravel.getNumseats()),
                eq(CURRENCY.EUR)
        )).thenReturn(trips);
    }

}
