package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemMenu {
    CustomHolder menu = new CustomHolder(54, "§1Buy Items");
    ItemStack barrier = Utils.createItem(Material.BARRIER, 1, "§2Exit Item Shop");
    ItemStack diamond = Utils.createItem(Material.DIAMOND, 1, "Diamond");

    Icon Exit = new Icon(barrier).addClickAction(player -> {
       ShopMenu shopMenu = new ShopMenu(player);
    });

    Icon Diamond = new Icon(diamond).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player, diamond);
    });

    public ItemMenu(Player player) {
        menu.setIcon(49 ,Exit);
        menu.setIcon(0, Diamond);

        player.openInventory(menu.getInventory());
    }
}
