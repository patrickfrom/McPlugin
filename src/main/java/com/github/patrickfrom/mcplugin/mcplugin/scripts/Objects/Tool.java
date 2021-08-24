package com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects;

import org.bukkit.Material;

public class Tool {
    private Material pickaxe;
    private int gatherAmount;

    public Tool(Material pickaxe, int earn) {
        this.pickaxe = pickaxe;
        this.gatherAmount = earn;
    }

    public Material getPickaxe() {
        return pickaxe;
    }

    public int getGatherAmount() {
        return gatherAmount;
    }
}
