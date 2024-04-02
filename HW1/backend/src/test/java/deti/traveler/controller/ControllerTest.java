package deti.traveler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import deti.traveler.entity.Model.TravelModel;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    private final Travel DummyTravel = new Travel(1L, "Dublin, Ireland","Galway, Ireland",LocalDateTime.now(),  LocalDateTime.now(), 2, 11.99);


    @Test
    void TestSearchByGivenCities() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String travelModelJson = objectMapper.writeValueAsString(new TravelModel(DummyTravel.getFromcity(), DummyTravel.getTocity(), DummyTravel.getPrice(), DummyTravel.getDeparture(), DummyTravel.getNumseats()));

        mockController.perform(get("/cities/EUR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(travelModelJson)
                )
                .andDo(print())
                .andExpect(status().isFound());
    }



    @BeforeEach
    void setupTest() throws IOException, InterruptedException {
        final List<Travel> trips = new ArrayList<>();
        trips.add(DummyTravel);

        when(service.getTravel(
                eq(DummyTravel.getFromcity()),
                eq(DummyTravel.getTocity()),
                any(LocalDateTime.class),
                eq(DummyTravel.getNumseats()),
                eq(CURRENCY.EUR)
        )).thenReturn(trips);
    }

}
