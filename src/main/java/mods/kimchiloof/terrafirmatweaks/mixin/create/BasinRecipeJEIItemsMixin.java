package mods.kimchiloof.terrafirmatweaks.mixin.create;

import com.simibubi.create.compat.jei.category.BasinCategory;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mods.kimchiloof.terrafirmatweaks.config.TweaksConfig;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.capabilities.heat.Heat;
import net.dries007.tfc.config.TFCConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mixin(BasinCategory.class)
public class BasinRecipeJEIItemsMixin {
    // Add charcoal forge fuels to the recipe layout
    // Add bellows to the recipe layout when superheated required
    @Inject(
            at = @At(
                    value = "FIELD",
                    target = "Lcom/simibubi/create/content/processing/burner/BlazeBurnerBlock$HeatLevel;NONE:Lcom/simibubi/create/content/processing/burner/BlazeBurnerBlock$HeatLevel;"
            ),
            method = "setRecipe(Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;Lcom/simibubi/create/content/processing/basin/BasinRecipe;Lmezz/jei/api/recipe/IFocusGroup;)V",
            remap = false,
            cancellable = true)
    private void setRecipe(IRecipeLayoutBuilder builder, BasinRecipe recipe, IFocusGroup focuses, CallbackInfo ci) {
        HeatCondition requiredHeat = recipe.getRequiredHeat();

        if (!requiredHeat.testBlazeBurner(BlazeBurnerBlock.HeatLevel.NONE)) {
            // Get a list of all valid charcoal forge fuels
            List<ItemStack> charcoalForgeFuels = new ArrayList<>();
            Objects.requireNonNull(ForgeRegistries.ITEMS.tags())
                    .getTag(TFCTags.Items.FORGE_FUEL).stream().toList().forEach(
                            item -> charcoalForgeFuels.add(item.getDefaultInstance())
                    );

            builder
                    .addSlot(RecipeIngredientRole.RENDER_ONLY, 134, 81)
                    .addItemStacks(charcoalForgeFuels);
        }

        if (!requiredHeat.testBlazeBurner(BlazeBurnerBlock.HeatLevel.KINDLED)) {
            builder
                    .addSlot(RecipeIngredientRole.CATALYST, 153, 81)
                    .addItemStack(TFCBlocks.BELLOWS.get().asItem().getDefaultInstance());
        }

        ci.cancel();
    }

    // Replace "Heated" and "SuperHeated" with TFC heat levels
    @Inject(
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I"
            )},
            method = {"draw(Lcom/simibubi/create/content/processing/basin/BasinRecipe;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/client/gui/GuiGraphics;DD)V"},
            cancellable = true
    )
    private void draw(BasinRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY, CallbackInfo ci) {
        HeatCondition recipeHeat = recipe.getRequiredHeat();

        if (recipeHeat != HeatCondition.NONE) {
            Heat requiredHeat = switch (recipeHeat) {
                case HEATED -> TweaksConfig.CREATE.BASIN_HEAT_LEVEL.kindling.get();
                case SUPERHEATED -> TweaksConfig.CREATE.BASIN_HEAT_LEVEL.seething.get();
                default -> null;
            };

            if (requiredHeat != null) {
                MutableComponent requiredHeatText = (TFCConfig.CLIENT.heatTooltipStyle.get()).formatColored(requiredHeat.getMin());
                if (requiredHeatText != null) {
                    graphics.drawString(
                            Minecraft.getInstance().font,
                            requiredHeatText.getVisualOrderText(),
                            9, 86,
                            0xFFFFFF,
                            false
                    );
                }
            }

            ci.cancel();
        }
    }
}
