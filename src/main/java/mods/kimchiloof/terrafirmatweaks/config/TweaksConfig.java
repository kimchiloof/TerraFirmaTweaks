package mods.kimchiloof.terrafirmatweaks.config;

import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import mods.kimchiloof.terrafirmatweaks.util.MixinUtils;
import net.dries007.tfc.common.capabilities.heat.Heat;
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
                        .defineEnum("seethingBasinHeat", Heat.BRILLIANT_WHITE);

                kindling = builder
                        .comment("The min heat level at which a charcoal forge is considered kindling (heating)")
                        .defineEnum("kindlingBasinHeat", Heat.ORANGE);

                smouldering = builder
                        .comment("The min heat level at which a charcoal forge is considered smouldering (passively heating)")
                        .defineEnum("smoulderingBasinHeat", Heat.FAINT_RED);

                builder.pop();
            }
            public final EnumValue<Heat> seething;
            public final EnumValue<Heat> kindling;
            public final EnumValue<Heat> smouldering;
        }

        public final IntValue basinMaxFluidInput;

        // Create Categories
        public final Mixin MIXIN;
        public final BasinHeat BASIN_HEAT_LEVEL;

        // Create Configs
        Create(Builder builder) {
            builder.push("create");

            MIXIN = new Mixin(builder);
            BASIN_HEAT_LEVEL = new BasinHeat(builder);

            basinMaxFluidInput = builder
                    .comment("The max number of fluids that can be input into a basin at once")
                    .defineInRange("basinMaxFluidInput", 4, 4, Integer.MAX_VALUE);

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
