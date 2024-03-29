package deti.traveler.repository;

import deti.traveler.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long>
{
    public List<Travel> findByFromCityAndToCityAndDepartureAndNumSeatsIsGreaterThanEqual(String fromCity, String toCity, LocalDateTime departure, int numSeats);

    @Modifying
    @Query("update Travel t set t.numSeats = t.numSeats - :seatsBookeds where t.id = :id")
    public void updateTraveSeatslById(@Param("id") final Long id,@Param("seatsBookeds") final int seatsBookeds);
}
