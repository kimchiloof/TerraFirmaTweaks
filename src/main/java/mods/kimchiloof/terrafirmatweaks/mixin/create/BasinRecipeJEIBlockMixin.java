package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.animations.AnimatedBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import com.mojang.math.Axis;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.simibubi.create.compat.jei.category.animations.AnimatedKinetics.DEFAULT_LIGHTING;

@Mixin(AnimatedBlazeBurner.class)
public class BasinRecipeJEIBlockMixin {
    @Shadow(remap = false)
    private HeatLevel heatLevel;

    @Inject(at = @At("HEAD"), method = "draw", cancellable = true, remap = false)
    public void draw(GuiGraphics graphics, int xOffset, int yOffset, CallbackInfo ci) {
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 200);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Axis.YP.rotationDegrees(22.5f));
        int scale = 23;

        int charcoalForgeHeatLevel = Math.round( switch (heatLevel) {
            case SEETHING -> TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get().ordinal() / 10.0f * 7;
            case KINDLED -> TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get().ordinal() / 10.0f * 7;
            default -> 0;
        });

        GuiGameElement.of(
                TFCBlocks.CHARCOAL_FORGE.get().defaultBlockState()
                .setValue(CharcoalForgeBlock.HEAT, charcoalForgeHeatLevel)
        )
                .lighting(DEFAULT_LIGHTING)
                .atLocal(0, 1.65, 0)
                .scale(scale)
                .render(graphics);

        matrixStack.popPose();
        ci.cancel();
    }
}
