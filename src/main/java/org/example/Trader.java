package org.example;

public class Trader extends Person{
    private double Solde;
    private Portfolio <Asset> portfolio;

    public Trader(String nom, double solde) {
        super(nom);
        Solde = solde;
    }

    public double getSolde() {

        return Solde;
    }

    public void setSolde(double solde) {
        Solde = solde;
    }

    public Portfolio<Asset> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio<Asset> portfolio) {
        this.portfolio = portfolio;
    }
}
