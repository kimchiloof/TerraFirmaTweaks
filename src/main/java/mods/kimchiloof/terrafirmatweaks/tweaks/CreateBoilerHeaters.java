package mods.kimchiloof.terrafirmatweaks.tweaks;

import com.simibubi.create.content.fluids.tank.BoilerHeaters.Heater;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import net.dries007.tfc.common.blocks.devices.FirepitBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.simibubi.create.content.fluids.tank.BoilerHeaters.registerHeater;

public class CreateBoilerHeaters {
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (TweaksConfig.CREATE.MIXIN.enableCharcoalForgeCreateHeat.get())
                registerHeater(TFCBlocks.CHARCOAL_FORGE.get(), new CharcoalForgeBoilerHeater());
            if (TweaksConfig.CREATE.MIXIN.enableFirepitCreateHeat.get())
                registerHeater(TFCBlocks.FIREPIT.get(), new FirepitBoilerHeater());
        });
    }

    // CharcoalForgeBlock.HEAT heaters
    public static class CharcoalForgeBoilerHeater implements Heater {
        @Override
        public float getActiveHeat(Level level, BlockPos pos, BlockState state) {
            int charcoalForgeHeatLevel = state.getValue(CharcoalForgeBlock.HEAT);

            int seethingReq = Math.round(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get().ordinal() / 10.0f * 7);
            int kindlingReq = Math.round(TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get().ordinal() / 10.0f * 7);

            // Heat depending on the heat level of the forge
            if (charcoalForgeHeatLevel >= seethingReq) {
                // Superheated
                return 2;
            } else if (charcoalForgeHeatLevel >= kindlingReq) {
                // Heated
                return 1;
            } else {
                // Off
                return -1;
            }
        }
    }

    // FirepitBlock.LIT heaters
    public static class FirepitBoilerHeater implements Heater {
        @Override
        public float getActiveHeat(Level level, BlockPos pos, BlockState state) {
            if (!state.getValue(FirepitBlock.LIT)) {
                return -1;
            } else {
                // Kindling requirement too high for firepit
                if (TweaksConfig.CREATE.BASIN_HEAT_LEVEL.firepitKindles.get()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
