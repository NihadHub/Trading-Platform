package org.example;

public class Stock extends Asset{
    private String market;

    public Stock(String code, String name, double price, String market) {
        super(code, name, price);
        this.market = market;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String getType() {
        return "Stock";
    }

}
