package mods.kimchiloof.terrafirmatweaks.config;

import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import mods.kimchiloof.terrafirmatweaks.util.MixinUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TerraFirmaTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TweaksConfig {
    private static final Builder BUILDER = new Builder();

    // Config Categories
    public static class Create {
        public static class Mixin {
            Mixin(Builder builder) {
                builder.push("mixin");

                overrideBasinHeat = builder
                        .comment("Whether to allow the charcoal forge to heat Create basins and Steam engines")
                        .worldRestart()
                        .define(MixinUtils.Configs.BasinHeatMixin.toString(), true);

                overrideBasinJEIBlazeBurner = builder
                        .comment("Whether to override the JEI animation of the blaze burner to be a charcoal forge instead")
                        .worldRestart()
                        .define(MixinUtils.Configs.BasinRecipeJEIMixins.toString(), true);

                builder.pop();
            }
            public final BooleanValue overrideBasinHeat;
            public final BooleanValue overrideBasinJEIBlazeBurner;
        }

        public static class BasinHeat {
            BasinHeat(Builder builder) {
                builder.push("basinHeat");

                seething = builder
                        .comment("The min heat level at which a charcoal forge is considered seething (super-heating)")
                        .defineInRange("seething", 7, 0, 7);

                kindling = builder
                        .comment("The min heat level at which a charcoal forge is considered kindling (heating)")
                        .defineInRange("kindling", 5, 0, 7);

                smouldering = builder
                        .comment("The min heat level at which a charcoal forge is considered smouldering (passively heating)")
                        .defineInRange("smouldering", 1, 0, 7);

                builder.pop();
            }
            public final IntValue seething;
            public final IntValue kindling;
            public final IntValue smouldering;
        }

        // Create Categories
        public final Mixin MIXIN;
        public final BasinHeat BASIN_HEAT_LEVEL;

        // Create Configs
        Create(Builder builder) {
            builder.push("create");

            MIXIN = new Mixin(builder);
            BASIN_HEAT_LEVEL = new BasinHeat(builder);

            builder.pop();
        }
    }

    // Config Initialization
    public static final ForgeConfigSpec SPEC;
    public static final Create CREATE;

    static {
        CREATE = new Create(BUILDER);

        SPEC = BUILDER.build();
    }
}
