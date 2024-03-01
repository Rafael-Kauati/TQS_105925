package carsRenteable.demo.service;

import carsRenteable.demo.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CarsServiceImpl implements CarsService
{
    @Override
    public void save(Car car) {

    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public Optional<Car> getCarDetails(Long det) {
        return Optional.empty();
    }


}
