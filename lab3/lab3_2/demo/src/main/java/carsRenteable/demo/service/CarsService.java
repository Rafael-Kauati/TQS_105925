package carsRenteable.demo.service;

import carsRenteable.demo.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CarsService
{

    public void save(Car car);

    public List<Car> getAll();

    public Optional<Car> getCarDetails(Long det);
}
