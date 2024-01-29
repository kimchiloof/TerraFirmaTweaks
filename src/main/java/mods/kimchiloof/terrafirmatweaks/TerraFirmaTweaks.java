package mods.kimchiloof.terrafirmatweaks;

import com.mojang.logging.LogUtils;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfigGUI;
import mods.kimchiloof.terrafirmatweaks.util.recipes.RecipeConfigEnabledCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

@Mod(TerraFirmaTweaks.MOD_ID)
public class TerraFirmaTweaks {
    public static final String MOD_ID = "terrafirmatweaks";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String COMMON_CONFIG_FILE_NAME = MOD_ID + "-common.toml";

    public TerraFirmaTweaks() {
        configSetup();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::registerRecipeSerializers);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void configSetup() {
        // Register config
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                TweaksConfig.SPEC,
                COMMON_CONFIG_FILE_NAME
        );

        // Load config GUI
        if (ModList.get().isLoaded("cloth_config")) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> TweaksConfigGUI::registerConfigGUI);
        }
    }

    private void registerRecipeSerializers(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS)) {
            CraftingHelper.register(RecipeConfigEnabledCondition.Serializer.INSTANCE);
        }
    }

    public static ResourceLocation newRes(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
