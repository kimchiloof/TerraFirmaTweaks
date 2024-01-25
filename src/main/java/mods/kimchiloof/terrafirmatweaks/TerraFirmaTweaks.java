package mods.kimchiloof.terrafirmatweaks;

import com.mojang.logging.LogUtils;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfigGUI;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(TerraFirmaTweaks.MOD_ID)
public class TerraFirmaTweaks {
    public static final String MOD_ID = "terrafirmatweaks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TerraFirmaTweaks() {
        configSetup();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void configSetup() {
        // Register config
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                TweaksConfig.SPEC
        );

        // Load config GUI
        if (ModList.get().isLoaded("cloth_config")) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> TweaksConfigGUI::registerConfigGUI);
        }
    }
}
