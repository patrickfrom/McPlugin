package com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects;

import org.bukkit.Material;

public class Mineable {
    private Material type;
    private int chance;

    private Ore ore;
    private Material specialBlock;

    public Mineable(Ore ore, Material type, Material specialBlock, int chance) {
        this.ore = ore;
        this.type = type;
        this.specialBlock = specialBlock;
        this.chance = chance;
    }

    public Material getType() {
        return type;
    }

    public Ore getOre() {
        return ore;
    }

    public Material getSpecialBlock() {
        return specialBlock;
    }

    public int getChance() {
        return chance;
    }
}
