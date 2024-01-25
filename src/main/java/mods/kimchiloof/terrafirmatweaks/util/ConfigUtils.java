package mods.kimchiloof.terrafirmatweaks.util;

import me.shedaniel.clothconfig2.api.ValueHolder;
import net.minecraftforge.fml.ModList;

public class ConfigUtils {
    public static ValueHolder<Boolean> isModLoaded(final String modid) {
        return () -> ModList.get().isLoaded(modid);
    }
}
