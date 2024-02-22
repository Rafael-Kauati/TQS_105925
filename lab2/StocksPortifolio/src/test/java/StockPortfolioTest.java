import org.example.IStockmarketService;
import org.example.Stock;
import org.example.StocksPortfolio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class StockPortfolioTest
{
        @Mock
        private IStockmarketService service;


        @InjectMocks
        private StocksPortfolio portfolio;


        @Test
        void TestTotalValeu()
        {
            when(service.lookUpPrice("STEAM")).thenReturn(4.0);
            when(service.lookUpPrice("NVIDEA")).thenReturn(1.5);






        }
}

