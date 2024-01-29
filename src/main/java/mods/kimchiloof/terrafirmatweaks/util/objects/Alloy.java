package mods.kimchiloof.terrafirmatweaks.util.objects;

import java.util.HashMap;
import java.util.Map;

public class Alloy {
    private final String alloy;
    private final Map<String, PairDef> elements;
    private final float meltTemp;

    public Alloy(String alloy, Map<String, PairDef> elements, float meltTemp) {
        this.alloy = alloy;
        this.elements = new HashMap<>(elements);
        this.meltTemp = meltTemp;
    }

    public String getResultAlloy() {
        return alloy;
    }

    public Map<String, PairDef> getElements() {
        return elements;
    }
    public float getMeltTemp() {
        return meltTemp;
    }
}
