package mods.kimchiloof.terrafirmatweaks;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(TerraFirmaTweaks.MOD_ID)
public class TerraFirmaTweaks {
    public static final String MOD_ID = "terrafirmatweaks";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static TweaksConfig CONFIG;

    public TerraFirmaTweaks() {
        configSetup();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void configSetup() {
        AutoConfig.register(TweaksConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TweaksConfig.class).getConfig();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class, () ->
                        new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> AutoConfig.getConfigScreen(TweaksConfig.class, parent).get())
        ));
    }
}
