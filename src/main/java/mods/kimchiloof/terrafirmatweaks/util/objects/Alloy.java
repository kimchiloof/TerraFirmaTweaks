package mods.kimchiloof.terrafirmatweaks.util.objects;

import java.util.HashMap;
import java.util.Map;

public record Alloy(String alloy, Map<String, PairDef> elements, float meltTemp) {
    public Alloy(String alloy, Map<String, PairDef> elements, float meltTemp) {
        this.alloy = alloy;
        this.elements = new HashMap<>(elements);
        this.meltTemp = meltTemp;
    }
}
