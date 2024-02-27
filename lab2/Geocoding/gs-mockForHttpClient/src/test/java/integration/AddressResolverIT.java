package integration;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolverService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AddressResolverIT {


    private TqsBasicHttpClient client;

    private AddressResolverService adrResolver;

    @BeforeEach
    public void init(){
        client = new TqsBasicHttpClient();
        adrResolver = new AddressResolverService(client);
    }


    //@Disabled
    @Test
    @Disabled
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {


        Optional<Address> result = adrResolver.findAddressForLocation( 40.63436,-8.65616);

        Address expected = new Address( "Avenida da Universidade", "Aveiro", "", "3810-489");

        Assertions.assertAll("\nIf coordinates are correct, then return the address : \n",
                () ->   assertTrue( result.isPresent()),
                () -> assertEquals( expected, result.get())
        );

    }

    @Test
    @Disabled

    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertThrows(IllegalArgumentException.class, () -> adrResolver.findAddressForLocation( -361,-361));

    }

}
