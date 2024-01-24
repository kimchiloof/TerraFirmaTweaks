package mods.kimchiloof.terrafirmatweaks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;

@Config(name = TerraFirmaTweaks.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/mossy_cobblestone.png")
public class TweaksConfig implements ConfigData {
    @ConfigEntry.Category("create_tweaks")
    @ConfigEntry.Gui.TransitiveObject
    public TweaksCreateConfig.CreateTweaks create = new TweaksCreateConfig.CreateTweaks();
}