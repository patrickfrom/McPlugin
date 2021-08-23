package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreditsMenu {
    CustomHolder menu = new CustomHolder(9,"§1Credits");
    ItemStack noah = Utils.createItem(Material.LIME_CONCRETE,1,"§fNoahcv");
    ItemStack pat = Utils.createItem(Material.RED_CONCRETE,1,"§fPattech");
    ItemStack barrier = Utils.createItem(Material.BARRIER,1,"§4Exit");
    ItemStack  arrow = Utils.createItem(Material.ARROW,1,"§dBack");

    Icon Noahcv = new Icon(noah).addClickAction(player -> {

    });

    Icon Pattech = new Icon(pat).addClickAction(player -> {

    });

    Icon Exit = new Icon(barrier).addClickAction(player -> {
        player.closeInventory();
    });

    Icon Back = new Icon(arrow).addClickAction(player -> {
        MainMenu main = new MainMenu(player);
    });

    public CreditsMenu(Player player) {
        menu.setIcon(0, Noahcv);
        menu.setIcon(1, Pattech);
        menu.setIcon(8,Exit);
        menu.setIcon(7,Back);

        player.openInventory(menu.getInventory());
    }
}
