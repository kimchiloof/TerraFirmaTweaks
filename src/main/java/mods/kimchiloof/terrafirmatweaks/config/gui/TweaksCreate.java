package mods.kimchiloof.terrafirmatweaks.config.gui;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.SubCategoryListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.minecraft.network.chat.Component;

@SuppressWarnings("UnstableApiUsage")
public class TweaksCreate {
    private static BooleanListEntry mixinOverrideBasinHeat;
    private static BooleanListEntry mixinOverrideJEIBlazeBurner;

    public static SubCategoryListEntry BasinMixins(ConfigEntryBuilder ENTRY_BUILDER) {
        SubCategoryBuilder createMixinsCategoryBuilder = ENTRY_BUILDER.startSubCategory(Component.literal("Create Mixins"))
                .setTooltip(Component.literal("Changes require game restart"));

        mixinOverrideBasinHeat =
                ENTRY_BUILDER
                        .startBooleanToggle(
                                Component.literal("Use Basin Heat Override"),
                                TweaksConfig.CREATE.MIXIN.overrideBasinHeat.get()
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.MIXIN.overrideBasinHeat::set)
                        .setTooltip(Component.literal("Allow Charcoal Forge to heat Create basins and Steam engines"))
                        .setDefaultValue(false)
                        .build();

        mixinOverrideJEIBlazeBurner =
                ENTRY_BUILDER
                        .startBooleanToggle(
                                Component.literal("Use JEI Blaze Burner Override"),
                                TweaksConfig.CREATE.MIXIN.overrideJEIBlazeBurner.get()
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.MIXIN.overrideJEIBlazeBurner::set)
                        .setTooltip(Component.literal("Override JEI animation of the blaze burner to be a charcoal forge instead"))
                        .setDefaultValue(false)
                        .build();

        createMixinsCategoryBuilder.add(mixinOverrideBasinHeat);
        createMixinsCategoryBuilder.add(mixinOverrideJEIBlazeBurner);

        return createMixinsCategoryBuilder.build();
    }

    public static SubCategoryListEntry BasinHeats(ConfigEntryBuilder ENTRY_BUILDER) {
        SubCategoryBuilder basinHeatCategoryBuilder = ENTRY_BUILDER.startSubCategory(Component.literal("Basin Heat Override"))
                        .setTooltip(Component.literal("Requires \"Use Basin Heat Override\" to be enabled"));

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startIntSlider(
                                Component.literal("Seething Heat Level"),
                                TweaksConfig.CREATE.seethingHeatLevel.get(),
                                0,
                                7
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.seethingHeatLevel::set)
                        .setDefaultValue(7)
                        .setRequirement(Requirement.isTrue(mixinOverrideBasinHeat))
                        .setTooltip(Component.literal("The min heat level at which a charcoal forge is considered seething (super-heating)"))
                        .build()
        );

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startIntSlider(
                                Component.literal("Kindling Heat Level"),
                                TweaksConfig.CREATE.kindlingHeatLevel.get(),
                                0,
                                7
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.kindlingHeatLevel::set)
                        .setDefaultValue(3)
                        .setRequirement(Requirement.isTrue(mixinOverrideBasinHeat))
                        .setTooltip(Component.literal("The min heat level at which a charcoal forge is considered kindling (heating)"))
                        .build()
        );

        basinHeatCategoryBuilder.add(
                ENTRY_BUILDER
                        .startIntSlider(
                                Component.literal("Smouldering Heat Level"),
                                TweaksConfig.CREATE.smoulderingHeatLevel.get(),
                                0,
                                7
                        )
                        .setSaveConsumer(TweaksConfig.CREATE.smoulderingHeatLevel::set)
                        .setDefaultValue(1)
                        .setRequirement(Requirement.isTrue(mixinOverrideBasinHeat))
                        .setTooltip(Component.literal("The min heat level at which a charcoal forge is considered smouldering (passively heating)"))
                        .build()
        );

        return basinHeatCategoryBuilder.build();
    }


}
