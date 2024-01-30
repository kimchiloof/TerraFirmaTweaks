package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import com.simibubi.create.foundation.utility.BlockHelper;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import net.dries007.tfc.common.blocks.devices.FirepitBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BasinBlockEntity.class)
public class BasinHeatMixin {
    @Inject(at = @At("HEAD"), method = "getHeatLevelOf", cancellable = true, remap = false)
    private static void getHeatLevelOf(BlockState state, CallbackInfoReturnable<HeatLevel> cir) {
        if (state.hasProperty(CharcoalForgeBlock.HEAT) && TweaksConfig.CREATE.MIXIN.enableCharcoalForgeCreateHeat.get()) {
            // Charcoal forge heat (0-7)
            int charcoalForgeHeatLevel = state.getValue(CharcoalForgeBlock.HEAT);

            int seethingReq = Math.round(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get().ordinal() / 10.0f * 7);
            int kindlingReq = Math.round(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get().ordinal() / 10.0f * 7);
            int smoulderingReq = Math.round(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.smouldering.get().ordinal() / 10.0f * 7);

            // Heat depending on the heat level of the forge
            if (charcoalForgeHeatLevel >= seethingReq) {
                // Superheated
                cir.setReturnValue(HeatLevel.SEETHING);
            } else if (charcoalForgeHeatLevel >= kindlingReq) {
                // Heated
                cir.setReturnValue(HeatLevel.KINDLED);
            } else if (charcoalForgeHeatLevel >= smoulderingReq) {
                // Passive
                cir.setReturnValue(HeatLevel.SMOULDERING);
            } else {
                // Off
                cir.setReturnValue(HeatLevel.NONE);
            }
        } else if (state.hasProperty(FirepitBlock.LIT) && TweaksConfig.CREATE.MIXIN.enableFirepitCreateHeat.get()) {
            // Firepit heat
            if (!state.getValue(FirepitBlock.LIT)) {
                cir.setReturnValue(HeatLevel.NONE);
            } else {
                // Kindling requirement too high for firepit
                if (TweaksConfig.CREATE.BASIN_HEAT_LEVEL.firepitKindles.get()) {
                    cir.setReturnValue(HeatLevel.KINDLED);
                } else {
                    cir.setReturnValue(HeatLevel.SMOULDERING);
                }
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
