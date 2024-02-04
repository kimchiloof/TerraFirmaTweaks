package mods.kimchiloof.terrafirmatweaks.mixin.firmalife;

import com.eerussianguy.firmalife.common.blocks.OvenBottomBlock;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OvenBottomBlock.class)
public class BoundingBoxOvenBottomBlockMixin {
    @Final
    @Shadow(remap = false)
    public static final VoxelShape[] SHAPES = Helpers.computeHorizontalShapes(d -> Shapes.block());
}
