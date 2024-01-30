package mods.kimchiloof.terrafirmatweaks.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TweaksBlockTagGenerator extends BlockTagsProvider {
    public TweaksBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TerraFirmaTweaks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addPassiveBoilerTags();
        addInvisibleBlockTags();
        addForgeInsulationTags();
    }

    /**
     * Mod: Create
     * Tags all blocks that can passively heat a basin
     */
    private void addPassiveBoilerTags() {
       TFCBlocks.MAGMA_BLOCKS.forEach((rock, blockRegistryObject) ->
                this.tag(AllTags.AllBlockTags.PASSIVE_BOILER_HEATERS.tag)
                    .add(blockRegistryObject.get()));
    }

    /**
     * Mod: TFC
     * Tags all blocks that can sit on top of a charcoal forge
     */
    private void addInvisibleBlockTags() {
        TFCBlocks.METALS.forEach((defaultMetal, blockMap) ->
                blockMap.forEach((blockType, blockRegistryObject) -> {
                        if (
                                blockType.equals(Metal.BlockType.LAMP)
                                || blockType.equals(Metal.BlockType.BARS)
                                || blockType.equals(Metal.BlockType.CHAIN)
                                || blockType.equals(Metal.BlockType.TRAPDOOR)
                        ) {
                            this.tag(TFCTags.Blocks.FORGE_INVISIBLE_WHITELIST)
                                    .add(blockRegistryObject.get());
                        }
                })
        );

        this.tag(TFCTags.Blocks.FORGE_INVISIBLE_WHITELIST)
            // General
                .addTag(BlockTags.TRAPDOORS)
                .addTag(BlockTags.BUTTONS)
                .addTag(BlockTags.SIGNS)
                .addTag(BlockTags.ALL_SIGNS)
                .addTag(BlockTags.STANDING_SIGNS)
                .addTag(BlockTags.WALL_SIGNS)
                .addTag(BlockTags.ALL_HANGING_SIGNS)
                .addTag(BlockTags.WALL_HANGING_SIGNS)
                .addTag(BlockTags.CEILING_HANGING_SIGNS)
                .addTag(Tags.Blocks.GLASS_PANES)
                .add(Blocks.TORCH)

            // TFC
                .add(TFCBlocks.TORCH.get())

            // Create
                // Functional blocks
                .addOptional(AllBlocks.BASIN.getId())
                .addOptional(AllBlocks.FLUID_TANK.getId())
                .addOptional(AllBlocks.STEAM_ENGINE.getId())
                // Kinetics
                .addOptional(AllBlocks.SHAFT.getId())
                .addOptional(AllBlocks.COGWHEEL.getId())
                .addOptional(AllBlocks.LARGE_COGWHEEL.getId())
                .addOptional(AllBlocks.BELT.getId())
                .addOptional(AllBlocks.FLUID_PIPE.getId())
                .addOptional(AllBlocks.FLUID_VALVE.getId())
                .addOptional(AllBlocks.SMART_FLUID_PIPE.getId())
                .addOptional(AllBlocks.MECHANICAL_PUMP.getId())
                // Automation
                .addOptional(AllBlocks.CHUTE.getId())
                .addOptional(AllBlocks.SMART_CHUTE.getId())
                .addOptional(AllBlocks.ANDESITE_FUNNEL.getId())
                .addOptional(AllBlocks.BRASS_FUNNEL.getId())
                // Decoration
                .addOptional(AllBlocks.ANDESITE_SCAFFOLD.getId())
                .addOptional(AllBlocks.BRASS_SCAFFOLD.getId())
                .addOptional(AllBlocks.COPPER_SCAFFOLD.getId())
                .addOptional(AllBlocks.ANDESITE_BARS.getId())
                .addOptional(AllBlocks.BRASS_BARS.getId())
                .addOptional(AllBlocks.COPPER_BARS.getId())

            // Catch-all
                .addOptionalTag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag.location());
    }

    /**
     * Mod: TFC
     * Tags all blocks that can make up a charcoal forge wall
     */
    private void addForgeInsulationTags() {
        this.tag(TFCTags.Blocks.FORGE_INSULATION)
                .add(TFCBlocks.CHARCOAL_FORGE.get())
                .add(TFCBlocks.CHARCOAL_PILE.get());
    }
}
