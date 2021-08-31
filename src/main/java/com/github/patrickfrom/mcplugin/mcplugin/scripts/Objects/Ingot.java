package com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.Lists;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;
import org.bukkit.Material;

import java.util.Map;
import java.util.Random;

public class Ingot {
    Material material;
    Material smeltMaterial;
    int sellPrice;

    public Ingot(Material material, Material smeltMaterial,int sellPrice) {
        this.material = material;
        this.smeltMaterial = smeltMaterial;
        this.sellPrice = sellPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Material getMaterial() {
        return material;
    }

    public Material getSmeltMaterial() {
        return smeltMaterial;
    }

    public String getRandom() {
        Random ran = new Random();

        return Lists.rarities[ran.nextInt(8)];
    }
}