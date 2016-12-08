import yahoofinance.YahooFinance;
import yahoofinance.Stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by dacty on 12/6/2016.
 */
public class Sandbox {

    public static void main(String[] args) throws IOException {
        ArrayList<Stock> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("Program 8, Robert Phillips, masc0741");
        System.out.println("Please enter up a list of stock symbols separated by spaces:");

        String userInput = input.nextLine();
        String[] symbols = userInput.split(" ");

        for (String stock : symbols) {
            try {
                list.add(addStock(stock));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        listSort(list,"peg");
        printStocks(list);
        listSort(list,"Change");
        printStocks(list);
        //TODO: print the above list to a cool lookin chart

    }

    public static Stock addStock(String s) throws IOException {
        Stock stock = YahooFinance.get(s);
        return stock;
    }

    public static void printStocks(ArrayList<Stock> a) {
        System.out.println("NAME:\tPRICE:\tCHANGE:\tPEG:\tDIVIDEND:");
        for (int i = 0; i < a.size(); i++) {
            BigDecimal price = a.get(i).getQuote().getPrice();
            BigDecimal change = a.get(i).getQuote().getChangeInPercent();
            BigDecimal peg = a.get(i).getStats().getPeg();
            BigDecimal dividend = a.get(i).getDividend().getAnnualYieldPercent();
            String name = a.get(i).getSymbol();
            while (name.length() < 4) {
                name += " ";
            }
            System.out.println(name + "\t" + price + "\t" + change + "\t" + peg + "\t" + dividend);
        }
    }

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
