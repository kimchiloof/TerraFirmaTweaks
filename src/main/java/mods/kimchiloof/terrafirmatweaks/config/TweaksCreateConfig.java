package mods.kimchiloof.terrafirmatweaks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class TweaksCreateConfig {
    @Config(name = "create")
    public static final class CreateTweaks implements ConfigData {
        @ConfigEntry.Gui.CollapsibleObject
        @Comment("Create-related mixins (All changes require game restart)")
        public CreateMixins mixins = new CreateMixins();

        @ConfigEntry.BoundedDiscrete(min = 0, max = 7)
        @Comment("The min heat level at which a charcoal forge is considered seething (super-heating)")
        public int seethingHeatLevel = 7;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 7)
        @Comment("The min heat level at which a charcoal forge is considered kindling (heating)")
        public int kindlingHeatLevel = 4;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 7)
        @Comment("The min heat level at which a charcoal forge is considered smouldering (passively heating)")
        public int smoulderingHeatLevel = 1;
    }

    @Config(name = "create.mixins")
    public static class CreateMixins implements ConfigData {
        @Comment("Whether to allow the charcoal forge to heat Create basins and Steam engines")
        public boolean overrideBasinHeat = false;

        @Comment("Whether to override the JEI animation of the blaze burner to be a charcoal forge instead")
        public boolean overrideJEIBlazeBurner = false;

    }
}
