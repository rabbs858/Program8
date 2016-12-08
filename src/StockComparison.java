import yahoofinance.YahooFinance;
import yahoofinance.Stock;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program #8
 *
 * @author Rob Phillips / Adam Hancock
 * @description This class uses Stjin Strickx's Yahoo Finance API, obtained from financequotes-api.com. Allows the user
 * to enter as many stock symbols as they wish and compares all of them to find the largest change.
 * CS108-2
 * @date 12/7/2016
 * @email rphillips@sdsu.edu / adamcollegemail8@gmail.com
 */
public class StockComparison {

    public static void main(String[] args) throws IOException, NullPointerException {
        ArrayList<Stock> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("Program 8, Robert Phillips, masc0741");
        System.out.println("Program 8, Adam Hancock, masc0715");
        System.out.println("Please enter up a list of stock symbols separated by spaces:");

        String userInput = input.nextLine();
        String[] symbols = userInput.split(" ");

        for (String stock : symbols) {
            try {
                list.add(getStock(stock));
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        listSort(list);
        printStocks(list);
    }

    /**
     * Retrieves stock data from the YahooFinance API.
     *
     * @param s A stock symbol to look up in the database.
     * @return A Stock class that includes the data retrieved from the API.
     * @throws IOException
     */
    public static Stock getStock(String s) throws IOException {
        Stock stock = YahooFinance.get(s);
        return stock;
    }

    /**
     * Prints the user-defined stocks in a tabulated chart. Includes name, price, change, PEG, and dividend.
     *
     * @param a An ArrayList of Stock objects.
     */
    public static void printStocks(ArrayList<Stock> a) {
        System.out.println("NAME:\t\tPRICE:\t\tCHANGE:\t\tPEG:\t\tDIVIDEND:");
        for (int i = 0; i < a.size(); i++) {
            BigDecimal price = a.get(i).getQuote().getPrice();
            BigDecimal change = a.get(i).getQuote().getChangeInPercent();
            BigDecimal peg = a.get(i).getStats().getPeg();
            BigDecimal dividend = a.get(i).getDividend().getAnnualYieldPercent();
            String name = a.get(i).getSymbol();
            while (name.length() < 4) {
                name += " ";
            }
            System.out.println(name + "\t\t" + price + "\t\t" + change + "\t\t" + peg + "\t\t" + dividend);
        }
    }

    /**
     * An insertion sort for sorting the ArrayList.
     *
     * @param a      An ArrayList of Stock objects.
     */
    public static void listSort(ArrayList<Stock> a) {

        for (int i = 1; i < a.size(); i++) {
            Stock key = a.get(i);
            int j;
            for (j = i - 1; j >= 0 && key.getQuote().getChangeInPercent().compareTo(a.get(j).getQuote().getChangeInPercent()) > 0; j--) {
                a.set(j + 1, a.get(j));
            }
            a.set(j + 1, key);
        }
    }
}
