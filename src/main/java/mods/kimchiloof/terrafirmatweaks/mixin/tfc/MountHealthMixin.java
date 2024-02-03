package mods.kimchiloof.terrafirmatweaks.mixin.tfc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dries007.tfc.client.IngameOverlays;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.dries007.tfc.config.HealthDisplayStyle;
import net.dries007.tfc.config.TFCConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(IngameOverlays.class)
public class MountHealthMixin {

    @Final
    @Shadow(remap = false)
    public static ResourceLocation TEXTURE;

    @Shadow(remap = false)
    private static boolean setupForSurvival(ForgeGui gui, Minecraft minecraft) { return false; }

    @Inject(
            at = @At("HEAD"),
            method = "renderMountHealth",
            remap = false,
            cancellable = true
    )
    private static void renderMountHealth(ForgeGui gui, GuiGraphics graphics, float partialTicks, int width, int height, CallbackInfo ci) {
        final Minecraft minecraft = Minecraft.getInstance();
        if (setupForSurvival(gui, minecraft))
        {
            final Player player = (Player) minecraft.getCameraEntity();
            assert player != null;

            if (player.getVehicle() instanceof final LivingEntity entity)
            {
                TerraFirmaTweaks$RenderMountHealthBar(entity, gui, graphics, width, height);
            }
        }
        ci.cancel();
    }

    /**
     * Removes the unnecessary parts of the renderHealthBar method for vehicles
     * Uses the yellow health bar for vehicles, red for absorption
     */
    @Unique
    private static void TerraFirmaTweaks$RenderMountHealthBar(LivingEntity entity, ForgeGui gui, GuiGraphics graphics, int width, int height)
    {
        final PoseStack stack = graphics.pose();
        HealthDisplayStyle style = TFCConfig.CLIENT.healthDisplayStyle.get();
        float maxHealth = entity.getMaxHealth();

        int centerX = width / 2;
        int y = height - gui.rightHeight;

        stack.pushPose();
        stack.translate(centerX + 1, y, 0);

        // Empty bar
        graphics.blit(TEXTURE, 0, 0, 90, 0, 90, 10);

        // Health calculations
        float absorption = entity.getAbsorptionAmount();
        absorption = Float.isNaN(absorption) ? 0 : absorption;
        float percentHealth = (entity.getHealth() + absorption) / entity.getMaxHealth();
        float currentHealth = percentHealth * maxHealth;
        percentHealth = Mth.clamp(percentHealth, 0, 1);

        // Health fill
        graphics.blit(TEXTURE, 0, 0, 90, 10, (int) (90 * percentHealth), 10);

        // Absorption
        float surplusPercent = Mth.clamp(percentHealth + (absorption / 20f) - 1, 0, 1);
        if (surplusPercent > 0)
        {
            float percent = Math.min(surplusPercent, 1);
            graphics.blit(TEXTURE, 0, 0, 0, 10, (int) (90 * percent), 10);
        }

        stack.popPose();

        String text = style.format(currentHealth, maxHealth);
        stack.pushPose();
        stack.translate(centerX + 46, y + 2.5, 0);
        stack.scale(0.8f, 0.8f, 1.0f);
        graphics.drawString(gui.getFont(), text, -1 * gui.getFont().width(text) / 2, 0, surplusPercent < 0.6 ? Color.WHITE.getRGB() : Color.BLACK.getRGB(), false);
        stack.popPose();

        gui.rightHeight += 10;
    }
}
