package mods.kimchiloof.tfc_tweaks_create.config;

import mods.kimchiloof.tfc_tweaks_create.TFCTweaksCreate;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = TFCTweaksCreate.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TweaksConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue SEETHING_HEAT_LEVEL = BUILDER
            .comment("The min heat level at which a charcoal forge is considered seething (super-heating)")
            .defineInRange("seethingHeatLevel", 7, 0, 7);

    private static final ForgeConfigSpec.IntValue KINDLING_HEAT_LEVEL = BUILDER
            .comment("The min heat level at which a charcoal forge is considered kindling (heating)")
            .defineInRange("kindlingHeatLevel", 4, 0, 7);

    private static final ForgeConfigSpec.IntValue SMOULDERING_HEAT_LEVEL = BUILDER
            .comment("The min heat level at which a charcoal forge is considered smouldering (passively heating)")
            .defineInRange("kindlingHeatLevel", 1, 0, 7);


    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int seethingHeatLevel;
    public static int kindlingHeatLevel;
    public static int smoulderingHeatLevel;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        seethingHeatLevel = SEETHING_HEAT_LEVEL.get();
        kindlingHeatLevel = KINDLING_HEAT_LEVEL.get();
        smoulderingHeatLevel = SMOULDERING_HEAT_LEVEL.get();
    }
}
