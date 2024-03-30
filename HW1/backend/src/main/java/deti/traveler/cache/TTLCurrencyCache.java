package deti.traveler.cache;

import deti.traveler.service.utils.CURRENCY;
import deti.traveler.service.utils.CurrencyConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TTLCurrencyCache {
    private final int TTL = 40; // seconds

    private CurrencyConverter converter;
    private long lastUpdateTime;

    public TTLCurrencyCache(CurrencyConverter converter) {
        this.converter = converter;
        this.lastUpdateTime = System.currentTimeMillis() / 1000;
    }

    public double convertValue(CURRENCY currency, double valueToBeConverted) throws IOException, InterruptedException {
        if (isCacheExpired()) {
            log.warn("\nCache TTL expired");
            updateCache();
        }
        return converter.convert(currency, valueToBeConverted);
    }

    public boolean isCacheExpired() {
        long currentTime = System.currentTimeMillis() / 1000;
        return currentTime >= lastUpdateTime + TTL;
    }

    private void updateCache() throws IOException, InterruptedException {
        log.warn("\nUpdating cache");
        converter.updateCacheCurrency();
        lastUpdateTime = System.currentTimeMillis() / 1000;
    }
}