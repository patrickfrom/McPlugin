package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.ArrayList;

public class Utils {
    public static ItemStack createItem(Inventory inventory, Material materialId, int amount, int invSlot, String displayName, String... loreString){
        ItemStack item = new ItemStack(materialId, amount);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItemByte(Inventory inventory, int materialId, int byteId, int amount, int invSlot, String displayName, String... loreString){
        ItemStack item;
        List<String> lore = new ArrayList<>();

        item = new ItemStack(Material.getMaterial(String.valueOf(materialId)), amount, (short) byteId);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        for(String string : loreString) {
            lore.add(string);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(invSlot - 1, item);
        return item;
    }
}
