
package carsRenteable.demo.service;

import carsRenteable.demo.entity.Car;
import carsRenteable.demo.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarsServiceImpl implements CarsService
{
    @Autowired
    private  CarRepository repository;


    @Override
    public void save(Car car)
    {
        repository.save(car);
    }

    @Override
    public List<Car> getAll()
    {
        return repository.findAll();
    }

    @Override
    public Optional<Car> getCarDetails(Long carid)
    {
        return repository.findById(carid);
    }


}
