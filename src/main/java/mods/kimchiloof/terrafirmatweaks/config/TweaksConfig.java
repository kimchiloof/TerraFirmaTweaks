package mods.kimchiloof.terrafirmatweaks.config;

import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TerraFirmaTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TweaksConfig {
    private static final Builder BUILDER = new Builder();

    // Config Categories
    public static class Create {
        public static class Mixin {
            Mixin(Builder BUILDER) {
                BUILDER.push("create_mixins");

                overrideBasinHeat = BUILDER
                        .comment("Whether to allow the charcoal forge to heat Create basins and Steam engines")
                        .worldRestart()
                        .define("overrideBasinHeat", false);

                overrideJEIBlazeBurner = BUILDER
                        .comment("Whether to override the JEI animation of the blaze burner to be a charcoal forge instead")
                        .worldRestart()
                        .define("overrideJEIBlazeBurner", false);

                BUILDER.pop();
            }
            public final BooleanValue overrideBasinHeat;
            public final BooleanValue overrideJEIBlazeBurner;
        }

        // Create Categories
        public final Mixin MIXIN;

        {
            MIXIN = new Mixin(BUILDER);
        }

        // Create Configs
        Create(Builder BUILDER) {
            BUILDER.push("create_heats");

            seethingHeatLevel = BUILDER
                    .comment("The min heat level at which a charcoal forge is considered seething (super-heating)")
                    .defineInRange("seethingHeatLevel", 7, 0, 7);

            kindlingHeatLevel = BUILDER
                    .comment("The min heat level at which a charcoal forge is considered kindling (heating)")
                    .defineInRange("kindlingHeatLevel", 3, 0, 7);

            smoulderingHeatLevel = BUILDER
                    .comment("The min heat level at which a charcoal forge is considered smouldering (passively heating)")
                    .defineInRange("smoulderingHeatLevel", 1, 0, 7);

            BUILDER.pop();
        }

        public final IntValue seethingHeatLevel;
        public final IntValue kindlingHeatLevel;
        public final IntValue smoulderingHeatLevel;
    }

    // Config Initialization
    public static final ForgeConfigSpec SPEC;
    public static final Create CREATE;

    static {
        CREATE = new Create(BUILDER);

        SPEC = BUILDER.build();
    }
}
