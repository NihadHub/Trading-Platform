package org.example;

public class Person {
    private  int id ;
    private static int idCounter = 1;
    private String nom;

    public Person(String nom) {
        this.id = idCounter++;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }
}
