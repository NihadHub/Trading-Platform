package org.example;

public class CryptoCurrency extends Asset{
    private String Type;

    public CryptoCurrency(String code, String name, double price, String type) {
        super(code, name, price);
        Type = type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
