package deti.traveler.cache;

import deti.traveler.service.utils.CURRENCY;
import deti.traveler.service.utils.CurrencyConverter;

import java.io.IOException;

public class TTLCurrencyCache {
    private final int TTL = 5; // seconds

    private CurrencyConverter converter;
    private long lastUpdateTime;

    public TTLCurrencyCache(CurrencyConverter converter) {
        this.converter = converter;
        this.lastUpdateTime = System.currentTimeMillis() / 1000;
    }

    public double convertValue(CURRENCY currency, double valueToBeConverted) throws IOException, InterruptedException {
        if (isCacheExpired()) {
            updateCache();
        }
        return converter.convert(currency, valueToBeConverted);
    }

    public boolean isCacheExpired() {
        long currentTime = System.currentTimeMillis() / 1000;
        return currentTime >= lastUpdateTime + TTL;
    }

    private void updateCache() throws IOException, InterruptedException {
        converter.updateCacheCurrency();
        lastUpdateTime = System.currentTimeMillis() / 1000;
    }
}