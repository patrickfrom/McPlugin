package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<Material, Material> mineables = new HashMap<Material, Material>() {{
        put(Material.COAL_ORE, Material.COAL);
        put(Material.COPPER_ORE, Material.COPPER_INGOT);
        put(Material.IRON_ORE, Material.IRON_INGOT);
        put(Material.REDSTONE_ORE, Material.REDSTONE);
        put(Material.GOLD_ORE, Material.GOLD_INGOT);
        put(Material.LAPIS_ORE, Material.LAPIS_LAZULI);
        put(Material.EMERALD_ORE, Material.EMERALD);
        put(Material.DIAMOND_ORE, Material.DIAMOND);
    }};

    public static Map<Material, Integer> tools = new HashMap<Material, Integer>() {{
        put(Material.FLINT, 1);
        put(Material.WOODEN_PICKAXE, 2);
    }};

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
