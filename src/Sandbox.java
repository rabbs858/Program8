import yahoofinance.YahooFinance;
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;
import yahoofinance.Stock;

/**
 * Created by dacty on 12/6/2016.
 *
 * TODO: connecting fine it looks like, now we need to do something cool with the data. check out the readme @ lib/README.md -rob
 */
public class Sandbox {

   public static void main(String[] args) throws IOException {

       ArrayList<Stock> list = new ArrayList<>();

       list.add(addStock("TMO"));
       System.out.println(list.toString());
   }

   public static Stock addStock(String s) throws IOException {
       Stock stock = YahooFinance.get(s);
       BigDecimal price = stock.getQuote().getPrice();
       BigDecimal change = stock.getQuote().getChangeInPercent();
       BigDecimal peg = stock.getStats().getPeg();
       BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
       return stock;
   }




}
