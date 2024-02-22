import org.example.IStockmarketService;
import org.example.Stock;
import org.example.StocksPortfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StockPortfolioTest
{


        @Mock
        private IStockmarketService service;


        @InjectMocks
        private StocksPortfolio portfolio;

        @Test
        void TestTotalValue()
        {
            when(service.lookUpPrice("STEAM")).thenReturn(4.0);
            when(service.lookUpPrice("NVIDEA")).thenReturn(1.5);
            /*
            * Start the test with the mock remote instance
            */


            portfolio.newStock(new Stock("STEAM", 4));
            portfolio.newStock(new Stock("NVIDEA", 2));

            final double total  = portfolio.totalValue();
            assertEquals(19.0, total);

            // 5. Verify the result (assert) and the use of the mock (verify)
            verify(service, times(2)).lookUpPrice(anyString());

        }

}

