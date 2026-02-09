package org.example;

import java.time.LocalDate;

public class Transaction {
    private Trader trader;
    private Asset asset;
    private String type;
    private int quantite;
    private double price;
    private LocalDate date;

    public Transaction(Trader trader, Asset asset, String type, int quantite, double price, LocalDate date) {
        this.trader = trader;
        this.asset = asset;
        this.type = type;
        this.quantite = quantite;
        this.price = price;
        this.date = date;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", asset=" + asset +
                ", type='" + type + '\'' +
                ", quantite=" + quantite +
                ", price=" + price +
                '}';
    }
}
