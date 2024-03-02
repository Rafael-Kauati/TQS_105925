package carsRenteable.demo;

import carsRenteable.demo.entity.Car;
import carsRenteable.demo.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CarsRenteableApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ITControllerTest
{

    @Autowired
    private MockMvc mockController;

    @Autowired
    private CarRepository repository;

    private final Car c1 = new Car(1L, "Subaru", "Impreza WRX"), c2  = new Car(2L,"Skoda", "Super B");
    private final List<Car> cars = Arrays.asList(c1,c2);

    @Test
    @Disabled
    void whenValidBodyForCar_thenCreatePostTheCar() throws IOException, Exception {
        mockController.perform(post("/api/cars/").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(c1)));

        Optional<Car> found = repository.findById(c1.getCarid());

        assertThat(found).isEqualTo(Optional.of(c1));
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() throws Exception {

        repository.saveAll(cars);
        System.out.println(repository.findAll());
        mockController.perform(get("/api/cars/").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].model", is(c1.getModel())))
                .andExpect(jsonPath("$[1].model", is(c2.getModel())));
    }


    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }
}
