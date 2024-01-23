package mods.kimchiloof.tfc_tweaks_create.mixin;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import com.simibubi.create.foundation.utility.BlockHelper;
import mods.kimchiloof.tfc_tweaks_create.config.TweaksConfig;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BasinBlockEntity.class)
public class BasinBlockEntityMixin {
    @Inject(at = @At("HEAD"), method = "getHeatLevelOf", cancellable = true, remap = false)
    private static void getHeatLevelOf(BlockState state, CallbackInfoReturnable<HeatLevel> callback) {
        if (state.getBlock().equals(TFCBlocks.CHARCOAL_FORGE.get())) {
            // Charcoal forge heat
            int heat = state.getValue(TFCBlockStateProperties.HEAT_LEVEL);

            // Heat depending on the heat level of the forge
            if (heat >= TweaksConfig.seethingHeatLevel)
                callback.setReturnValue(HeatLevel.SEETHING);
            else if (heat >= TweaksConfig.kindlingHeatLevel)
                callback.setReturnValue(HeatLevel.KINDLED);
            else if (heat >= TweaksConfig.smoulderingHeatLevel)
                callback.setReturnValue(HeatLevel.SMOULDERING);
            else
                callback.setReturnValue(HeatLevel.NONE);
        } else if (state.hasProperty(BlazeBurnerBlock.HEAT_LEVEL)) {
            // Blaze burner heat
            callback.setReturnValue(state.getValue(BlazeBurnerBlock.HEAT_LEVEL));
        } else {
            // Passive heater heat
            callback.setReturnValue(
                    AllTags.AllBlockTags.PASSIVE_BOILER_HEATERS.matches(state) && BlockHelper.isNotUnheated(state)
                            ? HeatLevel.SMOULDERING
                            : HeatLevel.NONE
            );
        }
    }
}
