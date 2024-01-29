package mods.kimchiloof.terrafirmatweaks;

import mods.kimchiloof.terrafirmatweaks.util.MixinEnums;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isConfigPresent;
import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isMixinConfigEnabled;
import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isModLoadedEarly;

public class TweaksMixinPluginManager implements IMixinConfigPlugin {
    private static final Map<String, Boolean> mixinApplyMap = Map.of(
            MixinEnums.Mixins.BasinHeatMixin.toString(),
                    isMixinConfigEnabled("create", MixinEnums.Configs.BasinHeatMixin),
            MixinEnums.Mixins.BasinMaxFluidInputMixin.toString(),
                    isModLoadedEarly("create"),
            MixinEnums.Mixins.BasinRecipeCoreMixin.toString(),
                    isModLoadedEarly("create"),
            MixinEnums.Mixins.BasinRecipeJEIBlockMixin.toString(),
                    isMixinConfigEnabled("create", MixinEnums.Configs.BasinRecipeJEIMixins),
            MixinEnums.Mixins.BasinRecipeJEIItemsMixin.toString(),
                    isMixinConfigEnabled("create", MixinEnums.Configs.BasinRecipeJEIMixins)
    );

    @Override
    public boolean shouldApplyMixin(String targetClassPathName, String mixinClassPathName) {
        if (!isConfigPresent()) return false;

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
