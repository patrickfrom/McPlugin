package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Mineable;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Ore;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Tool;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Material[] smeltables = {
            Material.RAW_COPPER,
            Material.RAW_IRON,
            Material.RAW_GOLD
    };

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

    public static ItemStack createItem(Material material, int amount, String displayName){
        ItemStack item = createItem(material,amount,displayName, "");
        return item;
    }

    public static ItemStack createItem(Material material, int amount, String displayName, String... lorestring){
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        List<String> lore = new ArrayList<>();
        for(String line : lorestring) {
            lore.add(line);
        }
        String[] emptyString = new String[] {""};
        if (lorestring == emptyString) {

        } else {
            itemMeta.setLore(lore);
        }

        item.setItemMeta(itemMeta);
        return item;
    }
}
