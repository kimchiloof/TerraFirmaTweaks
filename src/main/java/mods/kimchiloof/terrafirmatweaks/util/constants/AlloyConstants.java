package mods.kimchiloof.terrafirmatweaks.util.constants;

import mods.kimchiloof.terrafirmatweaks.util.objects.Alloy;
import mods.kimchiloof.terrafirmatweaks.util.objects.PairDef;
import net.dries007.tfc.util.Metal.Default;

import java.util.List;
import java.util.Map;

public class AlloyConstants {
    public static final List<Alloy> TFC_ALLOYS = List.of(
            new Alloy(
                    Default.BISMUTH_BRONZE.getSerializedName(),
                    Map.of(
                            Default.ZINC.getSerializedName(), new PairDef(0.25f, 0.2f, 0.3f),
                            Default.COPPER.getSerializedName(), new PairDef(0.6f, 0.5f, 0.65f),
                            Default.BISMUTH.getSerializedName(), new PairDef(0.15f, 0.1f, 0.2f)
                    )
            ),
            new Alloy(
                    Default.BLACK_BRONZE.getSerializedName(),
                    Map.of(
                            Default.COPPER.getSerializedName(), new PairDef(0.6f, 0.5f, 0.7f),
                            Default.SILVER.getSerializedName(), new PairDef(0.2f, 0.1f, 0.25f),
                            Default.GOLD.getSerializedName(), new PairDef(0.2f, 0.1f, 0.25f)
                    )
            ),
            new Alloy(
                    Default.BRASS.getSerializedName(),
                    Map.of(
                            Default.COPPER.getSerializedName(), new PairDef(0.9f, 0.88f, 0.92f),
                            Default.ZINC.getSerializedName(), new PairDef(0.1f, 0.08f, 0.12f)
                    )
            ),
            new Alloy(
                    Default.BRONZE.getSerializedName(),
                    Map.of(
                            Default.COPPER.getSerializedName(), new PairDef(0.9f, 0.88f, 0.92f),
                            Default.TIN.getSerializedName(), new PairDef(0.1f, 0.08f, 0.12f)
                    )
            ),
            new Alloy(
                    Default.ROSE_GOLD.getSerializedName(),
                    Map.of(
                            Default.COPPER.getSerializedName(), new PairDef(0.25f, 0.15f, 0.3f),
                            Default.GOLD.getSerializedName(), new PairDef(0.75f, 0.7f, 0.85f)
                    )
            ),
            new Alloy(
                    Default.STERLING_SILVER.getSerializedName(),
                    Map.of(
                            Default.COPPER.getSerializedName(), new PairDef(0.3f, 0.2f, 0.4f),
                            Default.SILVER.getSerializedName(), new PairDef(0.7f, 0.6f, 0.8f)
                    )
            ),
            new Alloy(
                    Default.WEAK_BLUE_STEEL.getSerializedName(),
                    Map.of(
                            Default.BLACK_STEEL.getSerializedName(), new PairDef(0.5f, 0.5f, 0.55f),
                            Default.STEEL.getSerializedName(), new PairDef(0.2f, 0.2f, 0.25f),
                            Default.BISMUTH_BRONZE.getSerializedName(), new PairDef(0.15f, 0.1f, 0.15f),
                            Default.STERLING_SILVER.getSerializedName(), new PairDef(0.15f, 0.1f, 0.15f)
                    )
            ),
            new Alloy(
                    Default.WEAK_RED_STEEL.getSerializedName(),
                    Map.of(
                            Default.BLACK_STEEL.getSerializedName(), new PairDef(0.5f, 0.5f, 0.55f),
                            Default.STEEL.getSerializedName(), new PairDef(0.2f, 0.2f, 0.25f),
                            Default.BRASS.getSerializedName(), new PairDef(0.15f, 0.1f, 0.15f),
                            Default.ROSE_GOLD.getSerializedName(), new PairDef(0.15f, 0.1f, 0.15f)
                    )
            ),
            new Alloy(
                    Default.WEAK_STEEL.getSerializedName(),
                    Map.of(
                            Default.STEEL.getSerializedName(), new PairDef(0.6f, 0.5f, 0.7f),
                            Default.NICKEL.getSerializedName(), new PairDef(0.2f, 0.15f, 0.25f),
                            Default.BLACK_BRONZE.getSerializedName(), new PairDef(0.2f, 0.15f, 0.25f)
                    )
            )
    );
}
