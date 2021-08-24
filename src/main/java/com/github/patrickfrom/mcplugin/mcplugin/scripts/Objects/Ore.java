package com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects;

import org.bukkit.Material;

public class Ore {
    String displayName;
    String[] lore = new String[] {"Price: "};

    Material material;
    int sellPrice;

    public Ore(String displayName, Material material, int sellPrice) {
        this.displayName = displayName;
        this.material = material;
        this.sellPrice = sellPrice;

        lore[0] += sellPrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String[] getLore() {
        return lore;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Material getMaterial() {
        return material;
    }
}
