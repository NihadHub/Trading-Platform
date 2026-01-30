package org.example;

public class Person {
    private static int id =1;
    private String nom;

    public Person(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
