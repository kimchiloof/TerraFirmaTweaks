package mods.kimchiloof.terrafirmatweaks.mixin.tfc;

import com.mojang.blaze3d.vertex.PoseStack;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.dries007.tfc.client.IngameOverlays;
import net.dries007.tfc.config.TFCConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IngameOverlays.class)
public class OverlayArmorMixin {
    @Unique
    private static final ResourceLocation TerraFirmaTweaks$TEXTURE = TerraFirmaTweaks.newRes("textures/gui/armor_overlay.png");

    @Shadow(remap = false)
    private static boolean setupForSurvival(ForgeGui gui, Minecraft minecraft) { return false; }

    @Shadow(remap = false)
    private static boolean enableThisOrThat(ResourceLocation id, boolean config, ResourceLocation myOverlay, ResourceLocation vanillaOverlay) { return false; }

    @Unique
    private static final ResourceLocation VANILLA_ARMOR = VanillaGuiOverlay.ARMOR_LEVEL.id();

    @Unique
    private static final ResourceLocation TWEAKS_ARMOR = new ResourceLocation("tfc", "tweaks_armor");

    @Inject(
            at = @At("HEAD"),
            method = "registerOverlays",
            remap = false
    )
    private static void registerOverlays(RegisterGuiOverlaysEvent event, CallbackInfo ci) {
        event.registerAbove(VANILLA_ARMOR, TWEAKS_ARMOR.getPath(), OverlayArmorMixin::TerraFirmaTweaks$RenderArmor);
    }

    @Inject(
            at = @At("TAIL"),
            method = "checkGuiOverlays",
            remap = false
    )
    private static void checkGuiOverlays(RenderGuiOverlayEvent.Pre event, CallbackInfo ci) {
        if (enableThisOrThat(event.getOverlay().id(), TFCConfig.CLIENT.enableHealthBar.get(), TWEAKS_ARMOR, VANILLA_ARMOR)) {
            event.setCanceled(true);
        }
    }

    @Unique
    private static void TerraFirmaTweaks$RenderArmor(ForgeGui gui, GuiGraphics graphics, float partialTicks, int width, int height)
    {
        final Minecraft minecraft = Minecraft.getInstance();
        if (setupForSurvival(gui, minecraft))
        {
            final Player player = (Player) minecraft.getCameraEntity();
            assert player != null;

            TerraFirmaTweaks$RenderArmorBar(player, gui, graphics, width, height);
        }
    }

    @Unique
    private static void TerraFirmaTweaks$RenderArmorBar(LivingEntity entity, ForgeGui gui, GuiGraphics graphics, int width, int height)
    {
        final PoseStack stack = graphics.pose();

        int armor = entity.getArmorValue();
        int maxArmor = 20;
        float percentArmor = (float) armor / maxArmor;

        int centerX = width / 2;
        int y = height - gui.leftHeight;

        stack.pushPose();
        stack.translate(centerX - 91, y, 0);

        graphics.blit(TerraFirmaTweaks$TEXTURE, 0, 0, 0, 0, 90, 10);

        graphics.blit(TerraFirmaTweaks$TEXTURE, 0, 0, 0, 10, (int) (90 * percentArmor), 10);

        stack.popPose();

        gui.leftHeight += 10;
    }
}
