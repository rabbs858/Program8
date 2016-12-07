import yahoofinance.YahooFinance
import yahoofinance.Stock;
import yahoofinance.Utils;
import java.math.BigDecimal;

/**
 * Created by dacty on 12/6/2016.
 */
public class Sandbox {

    Stock stock = YahooFinance.get("INTC");

    BigDecimal price = stock.getQuote().getPrice();
    BigDecimal change = stock.getQuote().getChangeInPercent();
    BigDecimal peg = stock.getStats().getPeg();
    BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

    stock.print();


}
