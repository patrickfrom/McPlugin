package com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects;

import org.bukkit.Material;

public class Mineable {
    private Material type;

    private Ore ore;

    public Mineable(Ore ore, Material type) {
        this.ore = ore;
        this.type = type;
    }

    public Ore getOre() {
        return ore;
    }

    public Material getType() {
        return type;
    }
}
