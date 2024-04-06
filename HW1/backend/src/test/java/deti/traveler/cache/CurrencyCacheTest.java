package deti.traveler.cache;


import deti.traveler.service.utils.CURRENCY;
import deti.traveler.service.utils.CurrencyConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyCacheTest
{
    private TTLCurrencyCache cache;

    @Test
    @Disabled
    public void testCacheExpired() throws IOException, InterruptedException {
        Thread.sleep(6000);
        assertTrue(cache.isCacheExpired());
    }

    @Test
    @Disabled
    public void testCacheUpdate() throws IOException, InterruptedException {
        Thread.sleep(6000);
        cache.convertValue(CURRENCY.USD, 200);
        assertFalse(cache.isCacheExpired());
    }


    @BeforeEach
    public void setuptest() throws IOException, InterruptedException
    {
        this.cache = new TTLCurrencyCache(new CurrencyConverter());
    }

}
