package mods.kimchiloof.terrafirmatweaks.util.objects;

import java.util.HashMap;
import java.util.Map;

public class Alloy {
    private final String alloy;
    private final Map<String, PairF> elements;

    public Alloy(String alloy, Map<String, PairF> elements) {
        this.alloy = alloy;
        this.elements = new HashMap<>(elements);
    }

    public String getResultAlloy() {
        return alloy;
    }

    public Map<String, PairF> getElements() {
        return elements;
    }
}
