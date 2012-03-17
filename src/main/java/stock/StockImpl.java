package stock;

import StockObjects.*;

public class StockImpl extends StockPOA {

    private Quote quote;
    private String description;

    public StockImpl(Quote quote, String description) {
        this.quote = quote;
        this.description = description;
    }

    public Quote get_quote() throws Unknown {
        if (quote == null) throw new Unknown("No known quote for " + description);
        else return quote;
    }

    public void set_quote(StockObjects.Quote stock_quote) {
        this.quote = stock_quote;
    }

    public String description() {
        return description;
    }
}