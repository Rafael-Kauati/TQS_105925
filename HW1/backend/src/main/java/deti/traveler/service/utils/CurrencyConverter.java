package deti.traveler.service.utils;

import deti.traveler.service.ApiCurrency;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CurrencyConverter
{
    private final ApiCurrency currency;

    private Map<String, Double> currencyValues;

    public double convert(CURRENCY currencyOpt, double toBeConverted)
    {
        System.out.println("/nChoosen currency : "+currencyOpt.toString());
        final double ExchangeRate = currencyValues.get(currencyOpt.toString());

        return toBeConverted * ExchangeRate;
    }

    public void updateCacheCurrency() throws IOException, InterruptedException {
        this.currency.Fetch_Currency();
        this.currencyValues = currency.getCurrencyMap();
    }

    public CurrencyConverter() throws IOException, InterruptedException {
        this.currency = new ApiCurrency();
        this.currency.Fetch_Currency();
        this.currencyValues = currency.getCurrencyMap();
    }

}
