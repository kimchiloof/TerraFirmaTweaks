package mods.kimchiloof.terrafirmatweaks.events;


import mods.kimchiloof.terrafirmatweaks.TerraFirmaTweaks;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModFileInfo;

public class AddPackFinderEvent {
    public static void findResourcePacks(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            IModFileInfo modFileInfo = ModList.get().getModFileById(TerraFirmaTweaks.MOD_ID);

            if (modFileInfo == null) {
                TerraFirmaTweaks.LOGGER.warn("Could not find TerraFirmaTweaks mod file info; built-in resource packs will be missing!");
                return;
            }

            event.addRepositorySource(consumer -> {
                Pack pack = Pack.readMetaAndCreate(
                        TerraFirmaTweaks.newRes("vexxed_tweaks").toString(),
                        Component.literal("Vexxed Tweaks"),
                        false,
                        id -> new PathPackResources(id, modFileInfo.getFile().findResource("resourcepacks/vexxed_tweaks"), false),
                        PackType.CLIENT_RESOURCES,
                        Pack.Position.TOP,
                        PackSource.BUILT_IN
                );

                if (pack != null) {
                    consumer.accept(pack);
                }
            });
        }
    }
}
