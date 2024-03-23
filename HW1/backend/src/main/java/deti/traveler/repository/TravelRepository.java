package deti.traveler.repository;

import deti.traveler.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long>
{
    public List<Travel> findByFromCityAndToCityAndDepartureAndNumSeatsIsGreaterThanEqual(String fromCity, String toCity, LocalDateTime Departure, int numSeats);
}
