package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.*;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopMenu {
    ShopUtils shopUtils = new ShopUtils();
    CustomHolder menu = new CustomHolder(9,"§1Shop");
    ItemStack barrier = Utils.createItem(Material.BARRIER, 1,"§4Back");
    ItemStack experience = Utils.createItem(Material.EXPERIENCE_BOTTLE, 1,"Buy Items");
    ItemStack brick = Utils.createItem(Material.BRICKS, 1,"Buy Blocks");
    ItemStack arrow = Utils.createItem(Material.ARROW,1,"§dBack");

    Icon exitButton = new Icon(barrier).addClickAction(player -> player.closeInventory());

    Icon Back = new Icon(arrow).addClickAction(player -> {
        MainMenu main = new MainMenu(player);
    });

    Icon Items = new Icon(experience).addClickAction(player -> {
        ItemMenu itemMenu = new ItemMenu(player);
    });

    Icon Blocks = new Icon(brick).addClickAction(player -> {

    });

    public ShopMenu(Player player) {
        menu.setIcon(8,exitButton);
        menu.setIcon(7,Back);
        menu.setIcon(0,Blocks);
        menu.setIcon(1,Items);

        player.openInventory(menu.getInventory());
    }
}
