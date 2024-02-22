package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor  // Add this annotation to generate a no-args constructor
@Data
public class StocksPortfolio {

    private  Collection<Stock> stocks ;
    private  IStockmarketService market;

    public StocksPortfolio(IStockmarketService market) {
        this.stocks = new ArrayList<>();
        this.market = market;
    }

    public void newStock(Stock s){ stocks.add(s); }

    public double totalValue()
    {
        double total = 0.0;

        for( Stock s : this.stocks )
        {
            total += s.getQuantity()
            * market.lookUpPrice(s.getLabel());

        }

        return total;
    }
}
