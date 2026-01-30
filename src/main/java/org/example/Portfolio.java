package org.example;
import java.util.HashMap;
import java.util.Map;

public class Portfolio<P extends Asset> {
    private Map<P,Integer> assets = new HashMap<>();

    public Map<P, Integer> getAssets() {
        return assets;
    }


}
