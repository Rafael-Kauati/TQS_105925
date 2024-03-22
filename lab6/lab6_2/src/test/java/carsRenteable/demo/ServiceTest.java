package carsRenteable.demo;

import carsRenteable.demo.entity.Car;
import carsRenteable.demo.repository.CarRepository;
import carsRenteable.demo.service.CarsService;
import carsRenteable.demo.service.CarsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ServiceTest
{
    @InjectMocks
    private CarsServiceImpl service;

    @Mock
    private CarRepository repository;

    final Car c1 = new Car(1L, "Subaru", "Impreza WRX"), c2  = new Car(2L,"Skoda", "Super B");
    final List<Car> cars = Arrays.asList(c1,c2);


    @Test
    void whenGivenValidCarId_thenReturnIt()
    {
        Car c = service.getCarDetails(2L).orElse(null);

        assertAll(
                "Given a valid car id, return it",
                () -> assertThat(c).isNotNull(),
                () -> assertEquals(c.getModel(), c2.getModel())
        );
    }

    @Test
    void requestForAllCars_thenTheyShouldBeReturned()
    {
        final List<Car> cs  = service.getAll();
        assertAll(
                "Should return all the 2 cars",
                () -> assertThat(cs.containsAll(cars))
        );

    }

    @Test
    public void whenGivenInvalidId_thenCarShouldNotBeFound() {
        final Optional<Car> not_found = service.getCarDetails(3L);
        assertThat(not_found).isEmpty();
    }



    @BeforeEach
    void envSetup()
    {

        given(repository.findById(c1.getCarid())).willReturn(Optional.of(c1));
        given(repository.findById(c2.getCarid())).willReturn(Optional.of(c2));


        given(repository.findById(3L)).willReturn(Optional.empty());

        given(repository.findAll()).willReturn(cars);

    }

}
