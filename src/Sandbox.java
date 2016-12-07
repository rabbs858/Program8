import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Map;
import java.math.BigDecimal;
import yahoofinance.Stock;

/**
 * Created by dacty on 12/6/2016.
 *
 * TODO: connecting fine it looks like, now we need to do something cool with the data. check out the readme @ lib/README.md -rob
 */
public class Sandbox {

   public static void main(String[] args) throws IOException {

       Stock stock = YahooFinance.get("INTC");

       BigDecimal price = stock.getQuote().getPrice();
       BigDecimal change = stock.getQuote().getChangeInPercent();
       BigDecimal peg = stock.getStats().getPeg();
       BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

       stock.print();


       /*Stock stock = YahooFinance.get("INTC");
       BigDecimal price = stock.getQuote(true).getPrice();
       System.out.println(price);
       System.err.println("Hello, test.");*/

   }


}
