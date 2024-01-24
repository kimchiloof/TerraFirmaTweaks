package mods.kimchiloof.terrafirmatweaks;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TweaksMixinPluginManager implements IMixinConfigPlugin {
    private static final Map<String, Boolean> MIXIN_CONFIGS = Map.of(
//            "BasinHeatMixin", TerraFirmaTweaks.CONFIG.create.mixins.overrideBasinHeat,
//            "BasinJEIBlazeBurnerBlockMixin", TerraFirmaTweaks.CONFIG.create.mixins.overrideJEIBlazeBurner,
//            "BasinJEIBlazeBurnerItemsMixin", TerraFirmaTweaks.CONFIG.create.mixins.overrideJEIBlazeBurner
    );

    @Override
    public boolean shouldApplyMixin(String targetClassPathName, String mixinClassPathName) {
        String mixinName = mixinClassPathName.substring(mixinClassPathName.lastIndexOf('.') + 1);
        boolean val = MIXIN_CONFIGS.getOrDefault(mixinName, true);
        TerraFirmaTweaks.LOGGER.warn("MIXIN ENABLER: {} -> {}", mixinName, val);
        return val;
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
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
}
