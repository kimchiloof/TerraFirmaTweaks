package mods.kimchiloof.terrafirmatweaks.config.gui;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.IntegerListEntry;
import me.shedaniel.clothconfig2.gui.entries.SubCategoryListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import mods.kimchiloof.terrafirmatweaks.util.ConfigUtils;
import net.dries007.tfc.common.capabilities.heat.Heat;
import net.minecraft.network.chat.Component;

@SuppressWarnings("UnstableApiUsage")
public class TweaksCreate {
    // Mixin Configs
    private static BooleanListEntry mixinOverrideJEIBlazeBurner;
    private static BooleanListEntry enableCharcoalForgeCreateHeat;
    private static BooleanListEntry enableFirepitCreateHeat;

    public static SubCategoryListEntry BasinMixins(ConfigEntryBuilder ENTRY_BUILDER) {
        SubCategoryBuilder createMixinsCategoryBuilder = ENTRY_BUILDER.startSubCategory(Component.literal("Create Mixins"))
                .setTooltip(Component.literal("Requires Create mod.\nChanges require game restart"))
                .setExpanded(true);

        mixinOverrideJEIBlazeBurner =
                ENTRY_BUILDER
                        .startBooleanToggle(
                                Component.literal("Show Charcoal Forge in JEI"),
                                TweaksConfig.CREATE.MIXIN.overrideBasinJEIBlazeBurner.get()
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.MIXIN.overrideBasinJEIBlazeBurner::set)
                        .setRequirement(Requirement.isTrue(ConfigUtils.isModLoadedConfig("create")))
                        .setTooltip(Component.literal("Override JEI animation of the blaze burner to be a charcoal forge instead"))
                        .requireRestart()
                        .build();

        enableCharcoalForgeCreateHeat =
                ENTRY_BUILDER
                        .startBooleanToggle(
                                Component.literal("Charcoal Forge Basin Heat Override"),
                                TweaksConfig.CREATE.MIXIN.enableCharcoalForgeCreateHeat.get()
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.MIXIN.enableCharcoalForgeCreateHeat::set)
                        .setRequirement(Requirement.isTrue(ConfigUtils.isModLoadedConfig("create")))
                        .setTooltip(Component.literal("Allow the Charcoal Forge to heat Create basins and Steam engines"))
                        .requireRestart()
                        .setDefaultValue(true)
                        .build();

        enableFirepitCreateHeat =
                ENTRY_BUILDER
                        .startBooleanToggle(
                                Component.literal("Firepit Basin Heat Override"),
                                TweaksConfig.CREATE.MIXIN.enableFirepitCreateHeat.get()
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.MIXIN.enableFirepitCreateHeat::set)
                        .setRequirement(Requirement.isTrue(ConfigUtils.isModLoadedConfig("create")))
                        .setTooltip(Component.literal("Allow Firepit to heat Create basins and Steam engines"))
                        .requireRestart()
                        .setDefaultValue(false)
                        .build();

        createMixinsCategoryBuilder.add(mixinOverrideJEIBlazeBurner);
        createMixinsCategoryBuilder.add(enableCharcoalForgeCreateHeat);
        createMixinsCategoryBuilder.add(enableFirepitCreateHeat);

        return createMixinsCategoryBuilder.build();
    }

    public static SubCategoryListEntry BasinHeats(ConfigEntryBuilder ENTRY_BUILDER) {
        SubCategoryBuilder basinHeatCategoryBuilder = ENTRY_BUILDER.startSubCategory(Component.literal("Basin Heat Override Options"))
                        .setTooltip(Component.literal("Requires any \"Basin Heat Override\" to be enabled"))
                        .setRequirement(Requirement.all(
                                Requirement.any(
                                        Requirement.isTrue(enableCharcoalForgeCreateHeat),
                                        Requirement.isTrue(enableFirepitCreateHeat)
                                ),
                                Requirement.isTrue(ConfigUtils.isModLoadedConfig("create"))
                        ));

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startEnumSelector(
                                Component.literal("Seething Heat Level"),
                                Heat.class,
                                TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get()
                        )
                        .setDefaultValue(Heat.BRILLIANT_WHITE)
                        .setSaveConsumer(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething::set)
                        .setTooltip(Component.literal("The min heat level at which a charcoal forge is considered seething (super-heating)"))
                        .build()
        );

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startEnumSelector(
                                Component.literal("Kindling Heat Level"),
                                Heat.class,
                                TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get()
                        )
                        .setDefaultValue(Heat.ORANGE)
                        .setSaveConsumer(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling::set)
                        .setTooltip(Component.literal("The min heat level at which a charcoal forge is considered kindling (heating)"))
                        .build()
        );

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startEnumSelector(
                                Component.literal("Smouldering Heat Level"),
                                Heat.class,
                                TweaksConfig.CREATE.BASIN_HEAT_LEVEL.smouldering.get()
                        )
                        .setDefaultValue(Heat.FAINT_RED)
                        .setSaveConsumer(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.smouldering::set)
                        .setTooltip(Component.literal("The min heat level at which a charcoal forge is considered smouldering (passively heating)"))
                        .build()
        );

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startBooleanToggle(
                                Component.literal("Firepit Kindles"),
                                TweaksConfig.CREATE.BASIN_HEAT_LEVEL.firepitKindles.get()
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.firepitKindles::set)
                        .setTooltip(Component.literal("When true, lit firepits are treated as 'kindling', otherwise 'smouldering'"))
                        .setRequirement(Requirement.isTrue(enableFirepitCreateHeat))
                        .setDefaultValue(false)
                        .build()
        );

        return basinHeatCategoryBuilder.build();
    }

    public static IntegerListEntry BasinMaxFluidInput(ConfigEntryBuilder ENTRY_BUILDER) {
        return ENTRY_BUILDER
                .startIntField(
                        Component.literal("Basin Max Fluid Input"),
                        TweaksConfig.CREATE.basinMaxFluidInput.get()
                )
                .setMin(4)
                .setDefaultValue(4)
                .setSaveConsumer(TweaksConfig.CREATE.basinMaxFluidInput::set)
                .setRequirement(Requirement.isTrue(ConfigUtils.isModLoadedConfig("create")))
                .setTooltip(Component.literal("The max numbers of fluids that can be input into a basin at once"))
                .build();
    }

    public static BooleanListEntry BasinAlloyRecipes(ConfigEntryBuilder ENTRY_BUILDER) {
        return ENTRY_BUILDER
                .startBooleanToggle(
                        Component.literal("Basin Alloy Recipes"),
                        TweaksConfig.CREATE.basinAlloyRecipes.get()
                )
                .setSaveConsumer(TweaksConfig.CREATE.basinAlloyRecipes::set)
                .setRequirement(Requirement.isTrue(ConfigUtils.isModLoadedConfig("create")))
                .setTooltip(Component.literal("Whether to add alloy recipes to the basin"))
                .setDefaultValue(true)
                .build();
    }
}
