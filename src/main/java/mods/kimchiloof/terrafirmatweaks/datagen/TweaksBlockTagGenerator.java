package mods.kimchiloof.terrafirmatweaks.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
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
    }

    /**
     * Mod: Create
     * Tags all blocks that can passively heat a basin
     */
    private void addPassiveBoilerTags() {
        this.tag(AllTags.AllBlockTags.PASSIVE_BOILER_HEATERS.tag)
                .add(TFCBlocks.FIREPIT.get());

        TFCBlocks.MAGMA_BLOCKS.forEach((rock, blockRegistryObject) ->
                this.tag(AllTags.AllBlockTags.PASSIVE_BOILER_HEATERS.tag)
                    .add(blockRegistryObject.get()));
    }

    /**
     * Mod: TFC
     * Tags all blocks that can sit on top of a charcoal forge
     */
    private void addInvisibleBlockTags() {
        this.tag(TFCTags.Blocks.FORGE_INVISIBLE_WHITELIST)
                .addOptional(AllBlocks.BASIN.getId())
                .addOptional(AllBlocks.FLUID_TANK.getId());
    }
}
