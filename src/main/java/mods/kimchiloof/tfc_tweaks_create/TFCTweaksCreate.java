package mods.kimchiloof.tfc_tweaks_create;

import com.mojang.logging.LogUtils;
import mods.kimchiloof.tfc_tweaks_create.config.TweaksConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TFCTweaksCreate.MODID)
public class TFCTweaksCreate {
    public static final String MODID = "tfc_tweaks_create";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TFCTweaksCreate() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TweaksConfig.SPEC);
    }
}
