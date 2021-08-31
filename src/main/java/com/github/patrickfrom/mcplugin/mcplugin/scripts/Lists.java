package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Ingot;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Mineable;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Ore;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Tool;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Lists {
    public static Ore[] ores = {
            new Ore("§8Coal", Material.COAL, 1),
            new Ore("§6Raw Copper", Material.RAW_COPPER, 4),
            new Ore("§fRaw Iron", Material.RAW_IRON, 8),
            new Ore("§eRaw Gold", Material.RAW_GOLD, 16),
            new Ore("§4Redstone", Material.REDSTONE, 32),
            new Ore("§9Lapiz Lazuli", Material.LAPIS_LAZULI, 64),
            new Ore("§2Emerald", Material.EMERALD, 128),
            new Ore("§bDiamond", Material.DIAMOND, 256),
    };

    public static String[] rarities = {
            "§8Garbage",
            "§7Broken",
            "§dBad",
            "§cRough",
            "Normal",
            "§5Sleek",
            "§3Fine",
            "§4Exquisite",
            "§6Perfect"
    };

    public static Ingot[] ingots = {
            new Ingot(Material.COPPER_INGOT, Material.RAW_COPPER,8),
            new Ingot(Material.IRON_INGOT, Material.RAW_IRON,16),
            new Ingot(Material.GOLD_INGOT, Material.RAW_GOLD,32),
    };

    public static Mineable[] mineables = {
            new Mineable(ores[0], Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE, 24),
            new Mineable(ores[1], Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE, 21),
            new Mineable(ores[2], Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE, 18),
            new Mineable(ores[3], Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE, 15),
            new Mineable(ores[4], Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE, 12),
            new Mineable(ores[5], Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE, 9),
            new Mineable(ores[6], Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE, 6),
            new Mineable(ores[7], Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, 3),
    };

    public static Tool[] tools = {
            new Tool(Material.FLINT, 1),
            new Tool(Material.WOODEN_PICKAXE, 2),
            new Tool(Material.STONE_PICKAXE, 4),
            new Tool(Material.IRON_PICKAXE, 8),
            new Tool(Material.GOLDEN_PICKAXE, 12),
            new Tool(Material.DIAMOND_PICKAXE, 16),
            new Tool(Material.NETHERITE_PICKAXE, 20),
            new Tool(Material.STONECUTTER, 24),
    };
}
