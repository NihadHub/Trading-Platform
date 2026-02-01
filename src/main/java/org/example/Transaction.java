package org.example;

import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private int quantite;
    private double price;


    public Transaction(String type, int quantite, double price) {
        this.type = type;
        this.quantite = quantite;
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


}
