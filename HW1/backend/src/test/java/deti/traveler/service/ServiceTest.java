package deti.traveler.service;

import deti.traveler.entity.Travel;
import deti.traveler.repository.TravelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ServiceTest
{
    @InjectMocks
    private TravelService service;

    @MockBean
    private TravelRepository repository;

    private final Travel DummyTravel = new Travel(1L, "Dublin, Ireland","Galway, Ireland", LocalDateTime.now(),  LocalDateTime.now(), 2, 11.99);


    @BeforeEach
    void setupTest()
    {
        MockitoAnnotations.openMocks(this);
    }
}
