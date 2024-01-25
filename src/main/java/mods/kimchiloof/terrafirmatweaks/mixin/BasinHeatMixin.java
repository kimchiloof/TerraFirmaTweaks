package mods.kimchiloof.terrafirmatweaks.mixin;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import com.simibubi.create.foundation.utility.BlockHelper;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BasinBlockEntity.class)
public class BasinHeatMixin {
    @Inject(at = @At("HEAD"), method = "getHeatLevelOf", cancellable = true, remap = false)
    private static void getHeatLevelOf(BlockState state, CallbackInfoReturnable<HeatLevel> cir) {
        if (state.hasProperty(CharcoalForgeBlock.HEAT)) {
            // Charcoal forge heat
            int heat = state.getValue(CharcoalForgeBlock.HEAT);

            // Heat depending on the heat level of the forge
            if (heat >= TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get()) {
                // Superheated
                cir.setReturnValue(HeatLevel.SEETHING);
            } else if (heat >= TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get()) {
                // Heated
                cir.setReturnValue(HeatLevel.KINDLED);
            } else if (heat >= TweaksConfig.CREATE.BASIN_HEAT_LEVEL.smouldering.get()) {
                // Passive
                cir.setReturnValue(HeatLevel.SMOULDERING);
            } else {
                // Off
                cir.setReturnValue(HeatLevel.NONE);
            }
        } else if (state.hasProperty(BlazeBurnerBlock.HEAT_LEVEL)) {
            // Blaze burner heat
            cir.setReturnValue(state.getValue(BlazeBurnerBlock.HEAT_LEVEL));
        } else {
            // Passive heater heat
            cir.setReturnValue(
                    AllTags.AllBlockTags.PASSIVE_BOILER_HEATERS.matches(state) && BlockHelper.isNotUnheated(state)
                            ? HeatLevel.SMOULDERING
                            : HeatLevel.NONE
            );
        }
        cir.cancel();
    }
}
