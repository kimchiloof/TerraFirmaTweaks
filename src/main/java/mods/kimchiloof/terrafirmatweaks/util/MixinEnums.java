package mods.kimchiloof.terrafirmatweaks.util;

public class MixinEnums {
    /**
     * The names of all mixins used by TerraFirmaTweaks.
     */
    public enum Mixins {
        BasinHeatMixin,
        BasinMaxFluidInputMixin,
        BasinRecipeCoreMixin,
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
}
