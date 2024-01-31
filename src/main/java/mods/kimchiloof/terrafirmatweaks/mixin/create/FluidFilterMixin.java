package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.simibubi.create.content.logistics.filter.FilterItem;
import mods.kimchiloof.terrafirmatweaks.util.ConfigEnums;
import mods.kimchiloof.terrafirmatweaks.util.ConfigUtils;
import net.dries007.tfc.common.capabilities.VesselLike;
import net.dries007.tfc.common.items.VesselItem;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FilterItem.class)
public class FluidFilterMixin {
    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/fluids/transfer/GenericItemEmptying;canItemBeEmptied(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Z"
            ),
            method = "test(Lnet/minecraft/world/level/Level;Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraft/world/item/ItemStack;Z)Z",
            remap = false,
            cancellable = true
    )
    private static void test(Level world, FluidStack stack, ItemStack filter, boolean matchNBT, CallbackInfoReturnable<Boolean> cir) {
        VesselLike vesselInventory = VesselItem.getInventoryVessel(filter);
        Metal metalFromFilter = Metal.getFromIngot(filter);

        // Vessel as filter - requires molten alloy
        if (vesselInventory != null && metalFromFilter != null) {
            cir.setReturnValue(vesselInventory.getFluidInTank(0).equals(stack));
            cir.cancel();
        }

        // Metal ingot as filter
        if (metalFromFilter != null && ConfigUtils.getConfigBooleanValue("create", ConfigEnums.General.EnableIngotFluidFilter.toString())) {
            cir.setReturnValue(metalFromFilter.getFluid().equals(stack.getFluid()));
            cir.cancel();
        }
    }
}
