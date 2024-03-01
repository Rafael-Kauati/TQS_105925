package carsRenteable.demo;


import carsRenteable.demo.controller.CarsController;
import carsRenteable.demo.entity.Car;
import carsRenteable.demo.service.CarsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(CarsController.class)
public class ControllerTest
{

    @MockBean
    private CarsService service;

    @Autowired
    private MockMvc MockController;

    final Car c1 = new Car(1L, "Subaru", "Impreza WRX"), c2  = new Car(2L,"Skoda", "Super B");

    @Test
    void whenRequestForCarIdExist_thenReturnTheCarDetails() throws Exception
    {
        when(service.getCarDetails(anyLong())).thenReturn(Optional.of(c1));

        // Perform the GET request using MockMvc
        MockController.perform(MockMvcRequestBuilders.get("/api/cars/{id}", Long.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Impreza WRX"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.maker").value("Subaru"))
                .andDo(print());
    }

    @Test
    void whenGetCars_thenReturnAllCars() throws  Exception
    {
        final Car c1 = new Car("Subaru", "Impreza WRX"), c2  = new Car("Skoda", "Super B");

        when(service.getAll()).thenReturn(Arrays.asList(c1,c2));

        MockController.perform(
                MockMvcRequestBuilders.get("/api/cars/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].maker").value("Subaru"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[1].maker").value("Skoda"))
                        .andDo(print());


    }

    @Test
    void whenPostACar_ThenCreateTheCar() throws  Exception
    {
        //The service method does not return an object, only a http status of created
        doNothing().when(service).save(Mockito.any(Car.class));

        MockController.perform(MockMvcRequestBuilders.post("/api/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(c2)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

}
