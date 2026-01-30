package org.example;

public class Trader extends Person{
    private double Solde;
    private Portfolio <Asset> portfolio;

    public Trader(String nom, double solde, Portfolio<Asset> portfolio) {
        super(nom);
        Solde = solde;
        this.portfolio = portfolio;
    }

    public double getSolde() {

        return Solde;
    }

    public void setSolde(double solde) {
        Solde = solde;
    }



}
