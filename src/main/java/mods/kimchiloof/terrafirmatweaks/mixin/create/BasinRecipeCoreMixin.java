package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.simibubi.create.content.processing.basin.BasinRecipe;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static mods.kimchiloof.terrafirmatweaks.util.ConfigUtils.isConfigPresent;

@Mixin(BasinRecipe.class)
public class BasinRecipeCoreMixin {
    @Inject(at = @At("HEAD"), method = "getMaxFluidInputCount", cancellable = true, remap = false)
    protected void getMaxFluidInputCount(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(
                isConfigPresent()
                        ? TweaksConfig.CREATE.basinMaxFluidInput.get()
                        : 4
        );

        cir.cancel();
    }
}
