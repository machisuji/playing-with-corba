package stock;

import StockObjects.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManagerPackage.State;

public class Client {
    public void createStock(String symbol, String description) {
        StockFactory factory = StockFactoryHelper.narrow(StockORB.lookup("StockFactory"));
        Stock stock = factory.create_stock(symbol, description);

        System.out.println("created stock");
    }

    public void createStock() {
        createStock("JAX", "Jax Alexander's Company");
    }

    public void setQuote(String symbol, double price) {
        Stock stock = getStock(symbol);
        Quote quote = new Quote(symbol, 42, price, 500);

        stock.set_quote(quote);
        System.out.println("set quote");
    }

    public void setQuote() {
        setQuote("JAX", 29.99);
    }

    public void readStock(String symbol) {
        Stock stock = getStock(symbol);
        try {
            Quote quote = stock.get_quote();

            System.out.println(symbol + " at " + quote.price);
        } catch (Unknown e) {
            System.err.println("Empty Stock");
        } catch (NullPointerException e) {
            System.err.println("Unknown stock '" + symbol + "'");
        }
    }

    public void readStock() {
        readStock("JAX");
    }

    public Stock getStock(String symbol) {
        org.omg.CORBA.Object stock = StockORB.lookup("stock-" + symbol);
        if (stock != null) {
            return StockHelper.narrow(stock);
        } else {
            return null;
        }
    }
}