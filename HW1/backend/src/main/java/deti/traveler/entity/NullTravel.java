package deti.traveler.entity;

import java.time.LocalDateTime;

public class NullTravel implements TravelInterface{
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public String getFromCity() {
        return null;
    }

    @Override
    public void setFromCity(String fromCity) {

    }

    @Override
    public String getToCity() {
        return null;
    }

    @Override
    public void setToCity(String toCity) {

    }

    @Override
    public LocalDateTime getDeparture() {
        return null;
    }

    @Override
    public void setDeparture(LocalDateTime departure) {

    }

    @Override
    public LocalDateTime getArrive() {
        return null;
    }

    @Override
    public void setArrive(LocalDateTime arrive) {

    }

    @Override
    public int getNumSeats() {
        return 0;
    }

    @Override
    public void setNumSeats(int numSeats) {

    }

    @Override
    public Double getPrice() {
        return null;
    }

    @Override
    public void setPrice(Double price) {

    }
}
