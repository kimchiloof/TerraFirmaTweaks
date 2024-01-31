package mods.kimchiloof.terrafirmatweaks.util;

import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.toml.TomlParser;
import me.shedaniel.clothconfig2.api.ValueHolder;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigUtils {
    // Mod loading
    public static ValueHolder<Boolean> isModLoadedConfig(final String modid) {
        return () -> ModList.get().isLoaded(modid);
    }

    public static boolean isModLoadedEarly(final String mod_id) {
        return FMLLoader.getLoadingModList().getModFileById(mod_id) != null;
    }

    // Config enabled
    public static boolean isRecipeConfigEnabled(String mod_id, ConfigEnums.Recipes configKey) {
        return ModList.get().isLoaded(mod_id)
                && getConfigBooleanValue(mod_id, configKey.toString());
    }

    public static boolean isMixinConfigEnabled(String mod_id, ConfigEnums.Mixins configKey) {
        return isModLoadedEarly(mod_id)
                && getConfigBooleanValue(mod_id, "mixin." + configKey.toString());
    }

    // Util config enabled
    private static boolean shownMissingConfigFileWarning = false;
    public static boolean isConfigPresent() {
        Path configPath = FMLPaths.CONFIGDIR.get().resolve(TerraFirmaTweaks.COMMON_CONFIG_FILE_NAME);

        if (Files.notExists(configPath)) {
            if (!shownMissingConfigFileWarning) {
                TerraFirmaTweaks.LOGGER.error("TerraFirmaTweaks config file not found, all tweaks temporarily disabled");
                shownMissingConfigFileWarning = true;
            }

            return false;
        }

        return true;
    }

    public static boolean getConfigBooleanValue(String mod_id, String config) {
        if (!isConfigPresent()) return false;

        Path configPath = FMLPaths.CONFIGDIR.get().resolve(TerraFirmaTweaks.COMMON_CONFIG_FILE_NAME);

        TomlParser parser = new TomlParser();
        Boolean isEnabled = parser.parse(configPath, FileNotFoundAction.READ_NOTHING).get(mod_id + "." + config);

        if (isEnabled == null) {
            TerraFirmaTweaks.LOGGER.error("Config file error: {} is null", config);
            return false;
        } else {
            return isEnabled;
        }
    }
}
