package carsRenteable.demo.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long carid;
    private final  String maker, model;
}
