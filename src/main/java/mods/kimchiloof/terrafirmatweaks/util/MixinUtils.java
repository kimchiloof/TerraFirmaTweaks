package mods.kimchiloof.terrafirmatweaks.util;

import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.toml.TomlParser;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
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

    private static boolean shownMissingConfigWarning = false;
    public static boolean isEarlyConfigEnabled(String mod_id, MixinUtils.Configs configKey) {
        String config = configKey.toString();

        Path configPath = FMLPaths.CONFIGDIR.get().resolve(TerraFirmaTweaks.COMMON_CONFIG_FILE_NAME);

        if (Files.notExists(configPath)) {
            if (!shownMissingConfigWarning) {
                TerraFirmaTweaks.LOGGER.warn("TerraFirmaTweaks config file not found, disabling mixins");
                shownMissingConfigWarning = true;
            }
            return false;
        }

        TomlParser parser = new TomlParser();
        Boolean mixinEnabled = parser.parse(configPath, FileNotFoundAction.READ_NOTHING).get(mod_id + ".mixin." + config);

        if (mixinEnabled == null) {
            TerraFirmaTweaks.LOGGER.error("Config file error: {} is null, disabling", config);
            return false;
        } else {
            return mixinEnabled;
        }
    }
    public static boolean isEarlyModLoaded(String mod_id) {
        return FMLLoader.getLoadingModList().getModFileById(mod_id) != null;
    }
}
