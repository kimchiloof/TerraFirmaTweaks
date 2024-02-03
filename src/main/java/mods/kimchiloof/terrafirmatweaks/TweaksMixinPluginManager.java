package mods.kimchiloof.terrafirmatweaks;

import mods.kimchiloof.terrafirmatweaks.util.MixinEnums.Mixins;
import mods.kimchiloof.terrafirmatweaks.util.ConfigEnums;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isMixinConfigEnabled;
import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isModLoadedEarly;

public class TweaksMixinPluginManager implements IMixinConfigPlugin {
    private static final Map<String, Boolean> mixinApplyMap = Map.of(
            Mixins.MountHealthMixin.toString(),
                    isMixinConfigEnabled("tfc", ConfigEnums.Mixins.MountHealthMixin),

            Mixins.BasinHeatMixin.toString(),
                    isMixinConfigEnabled("create", ConfigEnums.Mixins.CharcoalForgeHeat)
                        || isMixinConfigEnabled("create", ConfigEnums.Mixins.FirepitHeat),
            Mixins.BasinMaxFluidInputMixin.toString(),
                    isModLoadedEarly("create"),
            Mixins.BasinRecipeCoreMixin.toString(),
                    isModLoadedEarly("create"),
            Mixins.BasinRecipeJEIBlockMixin.toString(),
                    isMixinConfigEnabled("create", ConfigEnums.Mixins.BasinRecipeJEIMixins),
            Mixins.BasinRecipeJEIItemsMixin.toString(),
                    isMixinConfigEnabled("create", ConfigEnums.Mixins.BasinRecipeJEIMixins),
            Mixins.FluidFilterMixin.toString(),
                    isModLoadedEarly("create")
    );

    @Override
    public boolean shouldApplyMixin(String targetClassPathName, String mixinClassPathName) {
        String mixinName = mixinClassPathName.substring(mixinClassPathName.lastIndexOf('.') + 1);
        boolean isApplied = mixinApplyMap.getOrDefault(mixinName, false);

        TerraFirmaTweaks.LOGGER.info("TerraFirmaTweaks applying mixin: {} -> {}", mixinName, isApplied);
        return isApplied;
    }

    @Override
    public void onLoad(String s) {}
    @Override
    public String getRefMapperConfig() { return null; }
    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {}
    @Override
    public List<String> getMixins() { return null; }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
