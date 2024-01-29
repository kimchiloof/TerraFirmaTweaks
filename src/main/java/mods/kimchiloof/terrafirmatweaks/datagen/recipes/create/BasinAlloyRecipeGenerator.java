package mods.kimchiloof.terrafirmatweaks.datagen.recipes.create;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import mods.kimchiloof.terrafirmatweaks.util.RecipeEnums;
import mods.kimchiloof.terrafirmatweaks.util.constants.AlloyConstants;
import mods.kimchiloof.terrafirmatweaks.util.objects.Alloy;
import mods.kimchiloof.terrafirmatweaks.util.recipes.RecipeConfigEnabledCondition;
import net.dries007.tfc.common.capabilities.heat.Heat;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.PackOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BasinAlloyRecipeGenerator extends ProcessingRecipeGen {
    List<GeneratedRecipe> ALLOY_RECIPES = createAlloyRecipes();

    private List<GeneratedRecipe> createAlloyRecipes() {
        List<GeneratedRecipe> recipes = new ArrayList<>();

        for (Alloy alloy : AlloyConstants.TFC_ALLOYS) {
            recipes.add(
                    create(
                            TerraFirmaTweaks.newRes(alloy.getResultAlloy()),
                            processingRecipeBuilder -> {
                                    // Inputs
                                    AtomicInteger amountCheck = new AtomicInteger();

                                    alloy.getElements().forEach((element, pair) -> {
                                        processingRecipeBuilder.require(
                                                TFCFluids.METALS.get(Metal.Default.valueOf(element.toUpperCase())).source().get(),
                                                Math.round(pair.def() * 100)
                                        );

                                        amountCheck.addAndGet(Math.round(pair.def() * 100));
                                    });

                                    if (amountCheck.get() != 100) {
                                        throw new RuntimeException(
                                                "Total sum mismatch for "
                                                + alloy.getResultAlloy()
                                                + ". Expected: 100, got: "
                                                + amountCheck.get()
                                        );
                                    }

                                    // Outputs
                                    processingRecipeBuilder.output(
                                            TFCFluids.METALS.get(Metal.Default.valueOf(alloy.getResultAlloy().toUpperCase())).source().get(),
                                            100
                                    );

                                    processingRecipeBuilder.requiresHeat(
                                            // Not configurable - based on defaults
                                            // Will scale based on selected SUPERHEATED config value
                                            alloy.getMeltTemp() >= Heat.BRILLIANT_WHITE.getMin()
                                                    ? HeatCondition.SUPERHEATED
                                                    : HeatCondition.HEATED
                                    );
                                    processingRecipeBuilder.withCondition(
                                            new RecipeConfigEnabledCondition("create", RecipeEnums.Configs.CreateBasinAlloyRecipes)
                                    );

                                    return processingRecipeBuilder;
                            }
                    )
            );
        }

        return recipes;
    }

    public BasinAlloyRecipeGenerator(PackOutput generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.MIXING;
    }
}
