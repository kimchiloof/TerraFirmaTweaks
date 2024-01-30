package mods.kimchiloof.terrafirmatweaks.util.recipes;

import com.google.gson.JsonObject;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import mods.kimchiloof.terrafirmatweaks.util.RecipeEnums;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isRecipeConfigEnabled;

@Mod(TerraFirmaTweaks.MOD_ID)
public class RecipeConfigEnabledCondition implements ICondition {
    public static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS)) {
            CraftingHelper.register(Serializer.INSTANCE);
        }
    }

    private static final ResourceLocation NAME = TerraFirmaTweaks.newRes("config_enabled");
    private final String mod_id;
    private final RecipeEnums.Configs config;

    public RecipeConfigEnabledCondition(String mod_id, RecipeEnums.Configs config) {
        this.mod_id = mod_id;
        this.config = config;
    }
    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(IContext iContext) {
        return isRecipeConfigEnabled(mod_id, config);
    }

    public String toString() {
        return "config_enabled(\"" + this.config.toString() + "\")";
    }

    public static class Serializer implements IConditionSerializer<RecipeConfigEnabledCondition> {
        public static final Serializer INSTANCE = new Serializer();

        public Serializer() {}

        @Override
        public void write(JsonObject json, RecipeConfigEnabledCondition recipeConfig) {
            json.addProperty("mod_id", recipeConfig.mod_id);
            json.addProperty("config", recipeConfig.config.toString());
        }

        @Override
        public RecipeConfigEnabledCondition read(JsonObject json) {
            return new RecipeConfigEnabledCondition(
                    GsonHelper.getAsString(json, "mod_id"),
                    RecipeEnums.Configs.valueOf(GsonHelper.getAsString(json, "config"))
            );
        }

        @Override
        public ResourceLocation getID() {
            return RecipeConfigEnabledCondition.NAME;
        }
    }
}
