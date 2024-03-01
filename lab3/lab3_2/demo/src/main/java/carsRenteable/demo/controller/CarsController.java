package carsRenteable.demo.controller;

import carsRenteable.demo.entity.Car;
import carsRenteable.demo.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarsController
{

    @Autowired
    private final CarsService service;

    @GetMapping("/")
    public ResponseEntity<List<Car>> getAll()
    {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getDetails(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(service.getCarDetails(id), HttpStatus.FOUND);
    }


    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody Car car)
    {
        service.save(car);
        return new ResponseEntity<>("\nCar saved sucessfully", HttpStatus.CREATED);
    }



}
