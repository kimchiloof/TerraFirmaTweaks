package mods.kimchiloof.terrafirmatweaks.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import mods.kimchiloof.terrafirmatweaks.config.gui.TweaksCreate;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public class TweaksConfigGUI {
    public static ConfigBuilder getConfigScreen() {
        ConfigBuilder BUILDER = ConfigBuilder.create()
                .setTitle(Component.literal("TerraFirmaTweaks"));

        ConfigEntryBuilder ENTRY_BUILDER = BUILDER.entryBuilder();

        ConfigCategory CREATE_CATEGORY = BUILDER.getOrCreateCategory(Component.literal("Create"));

        CREATE_CATEGORY.addEntry(TweaksCreate.BasinMixins(ENTRY_BUILDER));
        CREATE_CATEGORY.addEntry(TweaksCreate.BasinHeats(ENTRY_BUILDER));
        CREATE_CATEGORY.addEntry(TweaksCreate.BasinMaxFluidInput(ENTRY_BUILDER));
        CREATE_CATEGORY.addEntry(TweaksCreate.BasinAlloyRecipes(ENTRY_BUILDER));

        return BUILDER;
    }

    public static void registerConfigGUI() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> TweaksConfigGUI.getConfigScreen()
                                .setParentScreen(parent)
                                .setTransparentBackground(client.level != null)
                                .build()
                )
        );
    }
}
