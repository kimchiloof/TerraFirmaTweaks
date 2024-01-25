package mods.kimchiloof.terrafirmatweaks.util;

import me.shedaniel.cloth.clothconfig.shadowed.com.moandjiezana.toml.Toml;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class MixinUtils {
    /**
     * The names of all mixins used by TerraFirmaTweaks.
     */
    public enum Mixins {
        BasinHeatMixin,
        BasinRecipeJEIBlockMixin,
        BasinRecipeJEIItemsMixin
    }

    /**
     * The names of the config paths used by TweaksConfig.
     */
    public enum Configs {
        BasinHeatMixin,
        BasinRecipeJEIMixins
    }

    public static boolean isEarlyConfigEnabled(String mod_id, MixinUtils.Configs configKey) {
        String config = configKey.toString();

        Path configPath = FMLPaths.CONFIGDIR.get().resolve(TerraFirmaTweaks.COMMON_CONFIG_FILE_NAME);
        Toml configFile;

        try {
            configFile = new Toml().read(configPath.toFile());
        } catch (Exception e) {
            TerraFirmaTweaks.LOGGER.error("TOML Config file error: {}", e.toString());
            return false;
        }

        Boolean mixinEnabled = configFile.getBoolean(mod_id + ".mixin." + config);

        if (mixinEnabled == null) {
            TerraFirmaTweaks.LOGGER.error("TOML Config file error: {} is null", config);
            return false;
        } else {
            return mixinEnabled;
        }
    }
    public static boolean isEarlyModLoaded(String mod_id) {
        return FMLLoader.getLoadingModList().getModFileById(mod_id) != null;
    }
}
