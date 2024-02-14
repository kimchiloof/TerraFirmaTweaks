<div align="center">
    <img src="https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/img/icon_rounded.png" alt="Icon" style="radius: 10px; width: 150px;">
    <h1>TerraFirmaTweaks</h1>
</div>

<p align="center">
    <a href="https://github.com/kimchiloof/TerraFirmaTweaks/commits/main/"><img src="https://img.shields.io/github/last-commit/kimchiloof/TerraFirmaTweaks" alt="Repo"></a>
    <a href="https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/LICENSE"><img src="https://img.shields.io/github/license/kimchiloof/TerraFirmaTweaks" alt="License"></a>
    <a href="https://github.com/kimchiloof/TerraFirmaTweaks/issues"><img src="https://img.shields.io/github/issues/kimchiloof/TerraFirmaTweaks" alt="Issues"></a>
</p>

TerraFirmaTweaks is a Minecraft 1.20.1 mod addon for [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft), adding better compatibility with vanilla Minecraft and other mods. 

The aim is to be a (mostly) all-in-one solution to TerraFirmaCraft bugs and incompatibilities, while offering as much user customizations as possible. 

<h3>Why make TerraFirmaTweaks?</h3>

1. A lack of some configuration options in previous tweaks mods
2. Tweaks were found in many different mods, some overlapping
3. Some tweaks missing from 1.20.1 TFC
4. Just wanted to try out modding :)

Feel free to open new issues documenting problems or suggestions, and pull requests if you have some changes you'd like to see added! I will review them as I have time.

## Available options 
Note that **non**-configurable options (i.e., those without options in the config file or another easy config method) are in _italics_

<details>
    <summary>TerraFirmaCraft <a href="https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft">@ Curseforge</a></summary>
    <ul>
        <li>Move and recolor mount health bar</li>
        <li>Add a TFC-style bar for armor</li>
        <li><i>Allow many blocks to cover the top of a charcoal forge, when it makes visual sense</i>†</li>
        <li><i>Allow the creation of multiple side by side charcoal forges</i>†</li>
        <li>Fix javelin, mangrove wood textures and add missing powder textures when using Vexxed Visuals*</li>
    </ul>
</details>

<details>
    <summary>Firmalife <a href="https://www.curseforge.com/minecraft/mc-mods/firmalife">@ Curseforge</a></summary>
    <ul>
        <li>Fix oven bounding box</li>
        <li>Fix oven textures when using Vexxed Visuals*</li>
    </ul>
</details>

<details>
    <summary>Create <a href="https://www.curseforge.com/minecraft/mc-mods/create">@ Curseforge</a></summary>
    <ul>
        <li>TFC charcoal forge and/or firepit provide heat to Create's basin and steam engine</li>
        <li>Replace the blaze burner in JEI for Create basin recipes with the TFC charcoal forge</li>
        <li>Replace the labels in JEI for Create basin recipes with TFC temperature and charcoal forge fuels</li>
        <li>Add recipes to allow TFC alloys to be made in the Create basin</li>
        <li>Allow vessel's with molten fluid and/or metal ingots to be used as fluid filters for their respective molten metals</li>
        <li><i>Add TFC magma blocks to act as passive heaters for steam boilers</i>†</li>
    </ul>
</details>

<sup>† These can instead be modified via datapacks (tags, recipes, etc.)</sup>

<sup>* Make sure to apply the Vexxed Tweaks resource pack included with the mod (and to put it <i>above</i> Vexxed Visuals and mod resources)</sup>

## Recommended Additions

Don't see a mod that needs tweaking? Submit a suggestion issue or PR, or look to any of the following other compatibility mods:

- Immersive Engineering via [TFC + IE Crossover](https://www.curseforge.com/minecraft/mc-mods/tfc-ie-crossover) and [Advanced TFC Tech Unofficial](https://www.curseforge.com/minecraft/mc-mods/advanced-tfc-tech-unofficial)
- Farmer's Delight via [Farmer's Delight TFC](https://www.curseforge.com/minecraft/mc-mods/farmers-delight-tfc)
- Small Ships via [TerraFirmaShips](https://www.curseforge.com/minecraft/mc-mods/terrafirmaships)

Want more tweaks? 

- I highly recommended to download and use [Vexxed Visuals](https://www.curseforge.com/minecraft/texture-packs/terrafirmacraft-vexxed-visuals) and the TerraFirmaTweaks built-in Vexxed Tweaks resource packs
- Use [Almost Unified](https://www.curseforge.com/minecraft/mc-mods/almost-unified) to hide duplicate ingots, etc. Read their wiki for more info on how.
    
## Installation

This mod requires:
- [Minecraft Forge](https://docs.minecraftforge.net/en/1.20.1/gettingstarted/)
- [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft)
- [Cloth Config](https://www.curseforge.com/minecraft/mc-mods/cloth-config) is not explictly required, but is highly recommended to offer a nice GUI config menu (a less preferred though still functional alternative can be Create's config menu or [Forge Config Screens](https://www.curseforge.com/minecraft/mc-mods/config-menus-forge))

To install, simply download the mod from this Github repo, the [Curseforge]() page, or the [Modrinth]() page, and drop into your 'mods' folder.

---

⚙️ **Note**: On the first game load where this mod is installed, all mixins are **disabled** as the config file is generated. 
- Most options' configs are set to enabled by default, so feel free to use the first load to disable any tweaks (especially if you have potentially incompatible mods)
- On game restart, all configs that are left as 'enabled' will be thereby enabled

⚙️ If you would like to use [Wooden Cog](https://www.curseforge.com/minecraft/mc-mods/wooden-cog)'s Create tweaks, you should:
1. Install both TerraFirmaTweaks and Wooden Cog
2. Run the game and **disable** all TerraFirmaTweak's Create options
3. Restart the game

---

🏗️ To build:

```bash
git clone https://github.com/kimchiloof/TerraFirmaTweaks/
cd TerraFirmaTweaks
./gradlew build
```

## Credits

Licensed under the [EUPL v1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12):
- Assets under `src/main/resources/assets` modified from [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft)
- Assets under `src/main/resources/resourcepacks/vexxed_tweaks` modified from [Vexxed Visuals](https://www.curseforge.com/minecraft/texture-packs/terrafirmacraft-vexxed-visuals) and TerraFirmaCraft

Some mixin code thanks to Serious Creeper's [TFC Tweaks (MIT)](https://www.curseforge.com/minecraft/mc-mods/tfc-tweaks-1-18-2) and [TFC Create (MIT)](https://www.curseforge.com/minecraft/mc-mods/tfc-create), Chauve Dev's [Wooden Cog (MIT)](https://www.curseforge.com/minecraft/mc-mods/wooden-cog)



## License

This mod is released under the [GPL-3.0](https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/LICENSE) license. However, "larger works" (i.e., modpacks) may reference this mod under the [LGPL-3.0](https://github.com/kimchiloof/TerraFirmaTweaks/blob/main/LICENSE.LESSER) license (i.e., modpacks including this mod (and do not include its source code) do not have to be licensed under GPL-3.0 or LGPL-3.0)
