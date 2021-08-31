package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {
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
