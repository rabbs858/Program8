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
 * to enter as many stock symbols as they wish and compares all of them to find the largest change. Can sort by other
 * options like PEG (price/earnings to growth ratio) and dividend, as well.
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
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        listSort(list, "Change");
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
     * An insertion sort. Not the most efficient, but done for academic purposes and learning how the insertion sort works.
     *
     * @param a      An ArrayList of Stock objects.
     * @param sortBy Category you wish to sort by. Default for this project is CHANGE.
     */
    public static void listSort(ArrayList<Stock> a, String sortBy) {
        if (sortBy.equals("peg")) {
            for (int i = 0; i < a.size(); i++) {
                for (int j = i; j > 0; j--) {
                    if (a.get(j).getStats().getPeg().compareTo(a.get(i).getStats().getPeg()) < 0) {
                        Stock temp = a.get(i);
                        a.set(i, a.get(j));
                        a.set(j, temp);
                    }
                }
            }
        } else {
            for (int i = 0; i < a.size(); i++) {
                for (int j = i; j > 0; j--) {
                    if (a.get(j).getQuote().getChangeInPercent().compareTo(a.get(i).getQuote().getChangeInPercent()) < 0) {
                        Stock temp = a.get(i);
                        a.set(i, a.get(j));
                        a.set(j, temp);
                    }
                }
            }
        }
    }
}
