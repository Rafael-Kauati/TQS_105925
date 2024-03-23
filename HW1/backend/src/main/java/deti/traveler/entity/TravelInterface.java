package deti.traveler.entity;

import java.time.LocalDateTime;

public interface TravelInterface {
    public Long getId();

    public void setId(Long id);

    public String getFromCity();

    public void setFromCity(String fromCity);

    public String getToCity();

    public void setToCity(String toCity);

    public LocalDateTime getDeparture();

    public void setDeparture(LocalDateTime departure);

    public LocalDateTime getArrive();

    public void setArrive(LocalDateTime arrive);

    public int getNumSeats();

    public void setNumSeats(int numSeats);

    public Double getPrice();

    public void setPrice(Double price);
}
