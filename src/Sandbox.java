import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Map;
import java.math.BigDecimal;
import yahoofinance.Stock;

/**
 * Created by dacty on 12/6/2016.
 */
public class Sandbox {

   public static void main(String[] args) throws IOException {

       Stock stock = YahooFinance.get("INTC");
       BigDecimal price = stock.getQuote(true).getPrice();
       System.out.println(price);

   }


}
