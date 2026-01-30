package org.example;

import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private int quantite;
    private double price;
    private LocalDateTime date;

    public Transaction(String type, int quantite, double price, LocalDateTime date) {
        this.type = type;
        this.quantite = quantite;
        this.price = price;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }
}
