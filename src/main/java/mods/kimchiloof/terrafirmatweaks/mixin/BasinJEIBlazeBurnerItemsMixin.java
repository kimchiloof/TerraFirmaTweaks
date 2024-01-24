package mods.kimchiloof.terrafirmatweaks.mixin;

import com.simibubi.create.compat.jei.category.BasinCategory;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
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
public class BasinJEIBlazeBurnerItemsMixin {
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

}
