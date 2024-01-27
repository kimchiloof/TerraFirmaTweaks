package mods.kimchiloof.terrafirmatweaks.util.objects;

public record PairF(float def, float min, float max) {

    public float getAverage() {
        return (min + max) / 2;
    }
}
