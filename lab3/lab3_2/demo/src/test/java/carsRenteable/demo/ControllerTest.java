package carsRenteable.demo;


import carsRenteable.demo.controller.CarsController;
import carsRenteable.demo.entity.Car;
import carsRenteable.demo.service.CarsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(CarsController.class)
public class ControllerTest
{

    @MockBean
    private CarsService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenRequestForCarIdExist_thenReturnTheCarDetails() throws Exception
    {
        // Mock the behavior of the service
        when(service.getCarDetails(anyLong())).thenReturn(Optional.of(new Car(1L)));

        // Perform the GET request using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/{id}", Long.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carid").value(1));
    }



    private final String carDetailResponseModel = "{\"carid\":1}";


    /*
     mvc.perform(
                post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(alex)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("alex")));
     */





}
