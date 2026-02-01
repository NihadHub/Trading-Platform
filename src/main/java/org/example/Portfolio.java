package org.example;
import java.util.HashMap;
import java.util.Map;

public class Portfolio<P extends Asset> {
    private int id;
    private Map<Asset, Integer> actifs = new HashMap<>();

    public Portfolio(int id) {
        this.id = id;
    }

    public Map<Asset, Integer> getActifs() {
        return actifs;
    }
}