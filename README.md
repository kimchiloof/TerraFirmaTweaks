<h1 align="center">TerraFirmaTweaks</h1>

<p align="center">
    <a href="https://github.com/kimchiloof/TerraFirmaTweaks"><img src="https://img.shields.io/github/last-commit/kimchiloof/TerraFirmaTweaks" alt="Repo"></a>
    <a href="https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/LICENSE"><img src="https://img.shields.io/github/license/kimchiloof/TerraFirmaTweaks" alt="License"></a>
    <a href="https://github.com/kimchiloof/TerraFirmaTweaks/issues"><img src="https://img.shields.io/github/issues/kimchiloof/TerraFirmaTweaks" alt="Issues"></a>
</p>

TerraFirmaTweaks is a Minecraft 1.20.1 mod addon for [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft), adding better compatibility with vanilla Minecraft, and various other mods. 

The aim is to be an all-in-one solution to TerraFirmaCraft bugs and incompatibilities, while offering as much user customizations as possible. 

<h3>Why make TerraFirmaTweaks?</h3>

1. A lack of some configuration options in previous tweaks mods
2. Tweaks were found in many different mods, some overlapping
3. Some tweaks missing from 1.20.1 TFC
4. Just wanted to try out modding :)

Feel free to open new issues documenting problems or suggestions, and pull requests if you have some changes you'd like to see added! I will review them as I have time.

## Available options 
Note that **non**-configurable options in _italics_

<details>
    <summary>Create <a href="https://www.curseforge.com/minecraft/mc-mods/create">@ Curseforge</a></summary>
    <ul>
        <li>TFC charcoal forge and/or firepit provide heat to Create's basin and steam engine</li>
        <li>Replace the blaze burner in JEI for Create basin recipes with the TFC charcoal forge</li>
        <li>Replace the labels in JEI for Create basin recipes with TFC temperature and charcoal forge fuels</li>
        <li>Add recipes to allow TFC alloys to be made in the Create basin</li>
        <li>Allow vessel's with molten fluid and/or metal ingots to be used as fluid filters for their respective molten metals</li>
    </ul>
</details>
    
## Installation

This mod requires:
- [Minecraft Forge](https://docs.minecraftforge.net/en/1.20.1/gettingstarted/)
- [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft)

To install, simply download the mod from this Github repo, the [Curseforge]() page, or the [Modrinth]() page, and drop into your 'mods' folder.

To build:

```bash
git clone https://github.com/kimchiloof/TerraFirmaTweaks/
cd TerraFirmaTweaks
./gradlew build
```

## Credits

Licensed under the [EUPL v1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12):
- Assets under `src/main/resources/assets` modified from [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft)
- Assets under `src/main/resources/resourcepacks/vexxed_tweaks` modified from [Vexxed Visuals](https://www.curseforge.com/minecraft/texture-packs/terrafirmacraft-vexxed-visuals)

Some mixin code thanks to Serious Creeper's [TFC Tweaks (MIT)](https://www.curseforge.com/minecraft/mc-mods/tfc-tweaks-1-18-2) and [TFC Create (MIT)](https://www.curseforge.com/minecraft/mc-mods/tfc-create), Chauve Dev's [Wooden Cog (MIT)](https://www.curseforge.com/minecraft/mc-mods/wooden-cog)



## License

This mod is released under the [GPL-3.0](https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/LICENSE) license. However, "larger works" (i.e., modpacks) may reference this mod under the [LGPL-3.0](https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/LICENSE.LESSER) license (i.e., modpacks including this mod (and do not include its source code) do not have to be licensed under GPL-3.0 or LGPL-3.0)
