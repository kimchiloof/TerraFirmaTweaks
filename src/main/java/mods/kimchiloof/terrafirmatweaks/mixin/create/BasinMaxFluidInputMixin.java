package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BasinBlockEntity.class)
public class BasinMaxFluidInputMixin {
    @Shadow(remap = false)
    public SmartFluidTankBehaviour inputTank;
    @Shadow(remap = false)
    private boolean contentsChanged;

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/foundation/blockEntity/behaviour/fluid/SmartFluidTankBehaviour;forbidInsertion()Lcom/simibubi/create/foundation/blockEntity/behaviour/fluid/SmartFluidTankBehaviour;"
            ),
            method="addBehaviours",
            remap = false
    )
    private void addBehaviours(List<BlockEntityBehaviour> behaviours, CallbackInfo ci) {
        this.inputTank = (new SmartFluidTankBehaviour(
                SmartFluidTankBehaviour.INPUT,
                (BasinBlockEntity) (Object) this,
                TweaksConfig.CREATE.basinMaxFluidInput.get(),
                1000,
                true
        )).whenFluidUpdates(() -> this.contentsChanged = true);
    }
}
