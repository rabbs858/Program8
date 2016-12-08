import yahoofinance.YahooFinance;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by dacty on 12/6/2016.
 */
public class Sandbox {

    public static void main(String[] args) throws IOException {
        ArrayList<Stock> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);

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

        //TODO: print the above list to a cool lookin chart
        list.toString();
    }

    public static Stock addStock(String s) throws IOException {
        Stock stock = YahooFinance.get(s);
        return stock;
    }

    public void printStocks(ArrayList<Stock> a) {

    }


}
