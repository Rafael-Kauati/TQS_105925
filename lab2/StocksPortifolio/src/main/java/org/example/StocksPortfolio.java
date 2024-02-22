package org.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@Data
public class StocksPortfolio {

    private final Collection<Stock> stocks ;
    private final IStockmarketService market;

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
