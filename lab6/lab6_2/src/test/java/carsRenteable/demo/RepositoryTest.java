package carsRenteable.demo;

import carsRenteable.demo.entity.Car;
import carsRenteable.demo.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class RepositoryTest
{
    @Autowired
    private TestEntityManager entMngr;

    @Autowired
    private CarRepository repository;

    private final Car c1 = new Car(1L, "Subaru", "Impreza WRX"), c2  = new Car(2L,"Skoda", "Super B");
    private final List<Car> cars = Arrays.asList(c1,c2);

    private Car mergedCar1 , mergedCar2 ;


    @Test
    void whenFindACarById_thenReturnTheCarDetails()
    {
        Optional<Car> c = repository.findById(c1.getCarid());

        assertAll(
                "Retrieve the car by ID from the database",
                (Executable) () -> assertTrue(c.isPresent()),
                () -> assertEquals(c.get().getCarid(), c1.getCarid()),
                () -> assertEquals(c.get().getMaker(), c1.getMaker()),
                () -> assertEquals(c.get().getModel(), c1.getModel())

        );
    }

    @Test
    void whenInvalidCarIdIsGiven_theReturnNull() {
        Optional<Car> c = repository.findById(10L);
        assertEquals(c, Optional.empty());
    }

    @Test
    void whenCalledToGetAll_thenReturnAllCars()
    {

        final List<Car> allCars = repository.findAll();

        assertThat(cars).hasSize(2).extracting(Car::getModel).containsOnly(c1.getModel(), c2.getModel());

    }

    @BeforeEach
    void setup()
    {
        this.mergedCar1 = entMngr.merge(c1);
        this.mergedCar2 = entMngr.merge(c2);


        entMngr.persist(mergedCar2);
        entMngr.persist(mergedCar1);

    }
}
