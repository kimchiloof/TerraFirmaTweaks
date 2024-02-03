package mods.kimchiloof.terrafirmatweaks.config;

import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import mods.kimchiloof.terrafirmatweaks.util.ConfigEnums;
import net.dries007.tfc.common.capabilities.heat.Heat;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TerraFirmaTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TweaksConfig {
    private static final Builder BUILDER = new Builder();

    // Config Categories
    public static class Tfc {
        public static class Mixin {
            Mixin(Builder builder) {
                builder.push("mixin");

                enableMountHealthOverlayFix = builder
                        .comment("Whether to revert the TFC mount health bar back to the vanilla location and color")
                        .worldRestart()
                        .define(ConfigEnums.Mixins.MountHealthMixin.toString(), true);

                builder.pop();
            }

            public final BooleanValue enableMountHealthOverlayFix;
        }

        // TFC Categories
        public final Mixin MIXIN;

        Tfc(Builder builder) {
            builder.push("tfc");

            MIXIN = new Mixin(builder);

            builder.pop();
        }
    }

    public static class Create {
        public static class Mixin {
            Mixin(Builder builder) {
                builder.push("mixin");

                overrideBasinJEIBlazeBurner = builder
                        .comment("Whether to override the JEI animation of the blaze burner to be a charcoal forge instead")
                        .worldRestart()
                        .define(ConfigEnums.Mixins.BasinRecipeJEIMixins.toString(), true);

                enableCharcoalForgeCreateHeat = builder
                        .comment("Whether to allow the charcoal forge to heat Create basins and Steam engines")
                        .worldRestart()
                        .define(ConfigEnums.Mixins.CharcoalForgeHeat.toString(), true);

                enableFirepitCreateHeat = builder
                        .comment("Whether to allow the firepit to heat Create basins and Steam engines")
                        .worldRestart()
                        .define(ConfigEnums.Mixins.FirepitHeat.toString(), false);

                builder.pop();
            }
            public final BooleanValue overrideBasinJEIBlazeBurner;
            public final BooleanValue enableCharcoalForgeCreateHeat;
            public final BooleanValue enableFirepitCreateHeat;
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

                firepitKindles = builder
                        .comment("Whether a lit firepit is considered kindling (heating)")
                        .define("firepitKindles", false);

                builder.pop();
            }
            public final EnumValue<Heat> seething;
            public final EnumValue<Heat> kindling;
            public final EnumValue<Heat> smouldering;
            public final BooleanValue firepitKindles;
        }

        public final IntValue basinMaxFluidInput;
        public final BooleanValue basinAlloyRecipes;
        public final BooleanValue enableIngotFluidFilter;

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

            basinAlloyRecipes = builder
                    .comment("Whether to add alloy recipes to the basin")
                    .define(ConfigEnums.Recipes.CreateBasinAlloyRecipes.toString(), true);

            enableIngotFluidFilter = builder
                    .comment("Whether to allow metal ingots to be used as fluid filters for Create pipes")
                    .define(ConfigEnums.General.EnableIngotFluidFilter.toString(), true);

            builder.pop();
        }
    }

    // Config Initialization
    public static final ForgeConfigSpec SPEC;
    public static final Tfc TFC;
    public static final Create CREATE;

    static {
        TFC = new Tfc(BUILDER);
        CREATE = new Create(BUILDER);

        SPEC = BUILDER.build();
    }
}
