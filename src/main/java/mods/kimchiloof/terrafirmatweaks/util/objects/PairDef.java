package mods.kimchiloof.terrafirmatweaks.util.objects;

public record PairDef(float def, float min, float max) {

    public float getAverage() {
        return (min + max) / 2;
    }
}
