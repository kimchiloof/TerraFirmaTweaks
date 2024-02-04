package mods.kimchiloof.terrafirmatweaks.config.gui;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.minecraft.network.chat.Component;

public class TweaksTfc {
    public static BooleanListEntry ArmorOverlay(ConfigEntryBuilder ENTRY_BUILDER) {
        return ENTRY_BUILDER
                .startBooleanToggle(
                        Component.literal("Armor Overlay"),
                        TweaksConfig.TFC.MIXIN.enableArmorOverlay.get()
                )
                .setSaveConsumer(TweaksConfig.TFC.MIXIN.enableArmorOverlay::set)
                .setTooltip(Component.literal("Whether to replace the armor HUD with a TFC-style overlay"))
                .setDefaultValue(true)
                .requireRestart()
                .build();
    }

    public static BooleanListEntry MountHealthOverlay(ConfigEntryBuilder ENTRY_BUILDER) {
        return ENTRY_BUILDER
                .startBooleanToggle(
                        Component.literal("Mount Health Bar Overlay Fix"),
                        TweaksConfig.TFC.MIXIN.enableMountHealthOverlayFix.get()
                )
                .setSaveConsumer(TweaksConfig.TFC.MIXIN.enableMountHealthOverlayFix::set)
                .setTooltip(Component.literal("Whether to revert the TFC mount health bar back to the vanilla location and color"))
                .setDefaultValue(true)
                .requireRestart()
                .build();
    }
}
