package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.animations.AnimatedBlazeBurner;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import com.mojang.math.Axis;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AnimatedBlazeBurner.class, remap = false)
public abstract class BasinRecipeJEIBlockMixin extends AnimatedKinetics {
    @Shadow
    private HeatLevel heatLevel;

    @Inject(at = @At("HEAD"), method = "draw", cancellable = true, remap = false)
    public void draw(GuiGraphics graphics, int xOffset, int yOffset, CallbackInfo ci) {
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 200);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Axis.YP.rotationDegrees(22.5f));
        int scale = 23;

        int charcoalForgeHeatLevel = switch (heatLevel) {
            case SEETHING -> TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get();
            case KINDLED -> TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get();
            default -> 0;
        };

        blockElement(
                TFCBlocks.CHARCOAL_FORGE.get().defaultBlockState()
                        .setValue(TFCBlockStateProperties.HEAT_LEVEL, charcoalForgeHeatLevel)
        )
                .atLocal(0, 1.65, 0)
                .scale(scale)
                .render(graphics);

        matrixStack.popPose();
        ci.cancel();
    }
}
