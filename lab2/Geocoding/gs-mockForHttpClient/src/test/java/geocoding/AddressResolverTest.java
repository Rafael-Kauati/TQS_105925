package geocoding;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {


    @InjectMocks
    AddressResolverService resolver;

    @Mock
    ISimpleHttpClient client;


    private String modelresponse = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"© 2024 MapQuest, Inc.\",\"imageUrl\":\"https://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2024 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":30.333472,\"lng\":-81.470448}},\"locations\":[{\"street\":\"12714 Ashley Melisse Blvd\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Jacksonville\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Duval\",\"adminArea4Type\":\"County\",\"adminArea3\":\"FL\",\"adminArea3Type\":\"State\",\"adminArea1\":\"US\",\"adminArea1Type\":\"Country\",\"postalCode\":\"32225\",\"geocodeQualityCode\":\"L1AAA\",\"geocodeQuality\":\"ADDRESS\",\"dragPoint\":false,\"sideOfStreet\":\"R\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":30.33472,\"lng\":-81.470448},\"displayLatLng\":{\"lat\":30.333472,\"lng\":-81.470448},\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v4/getmap?key=KEY&type=map&size=225,160&pois=purple-1,30.3334721,-81.4704483,0,0,|&center=30.3334721,-81.4704483&zoom=15&rand=-553163060\",\"nearestIntersection\":{\"streetDisplayName\":\"Posey Cir\",\"distanceMeters\":\"851755.1608527573\",\"latLng\":{\"longitude\":-87.523761,\"latitude\":35.013434},\"label\":\"Danley Rd & Posey Cir\"},\"roadMetadata\":{\"speedLimitUnits\":\"mph\",\"tollRoad\":null,\"speedLimit\":40}}]}]}";


    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {

        //todo: implement test; remove Disabled annotation

        String apiKey = ConfigUtils.getPropertyFromConfig("mapquest_key");

        URIBuilder uriBuilder =  new URIBuilder("https://www.mapquestapi.com/geocoding/v1/reverse");

        uriBuilder.addParameter("key", apiKey);

        uriBuilder.addParameter("location", (new Formatter()).format(Locale.US, "%.5f,%.5f", -87.523761, 35.013434).toString());

        System.out.println("\n\n=== "+uriBuilder.build().toString());

        lenient().when(client.doHttpGet(uriBuilder.build().toString())).thenReturn(modelresponse);





        // will crash for now...need to set the resolver before using it
        Optional<Address> result = resolver.findAddressForLocation(-87.523761, 35.013434);


        //return
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    @Disabled
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        ///todo: implement test


        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        // verify no valid result
        assertFalse( result.isPresent());

    }
}