package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainMenu {
    CustomHolder menu = new CustomHolder(9, "§1Main");
    ItemStack barrier = Utils.createItem(Material.BARRIER,1,"§4Exit");
    ItemStack note = Utils.createItem(Material.NOTE_BLOCK,1,"§9Shop");
    ItemStack gold = Utils.createItem(Material.GOLD_INGOT,1,"§6Credits");
    ItemStack bed = Utils.createItem(Material.RED_BED,1,"§2Spawn");

    Icon exitButton = new Icon(barrier).addClickAction(player -> player.closeInventory());

    Icon shop = new Icon(note).addClickAction(player -> {
        ShopMenu shopMenu = new ShopMenu(player);
    });

    Icon credits = new Icon(gold).addClickAction(player -> {
        CreditsMenu credits = new CreditsMenu(player);
    });

    Icon spawn = new Icon(bed).addClickAction(player -> {
        World world = player.getWorld();
        Location spawnLocation = new Location(world, 201,91,-285);
        player.teleport(spawnLocation);
    });

    public MainMenu(Player player) {
        menu.setIcon(8,exitButton);
        menu.setIcon(1,shop);
        menu.setIcon(0,credits);
        menu.setIcon(2,spawn);

        player.openInventory(menu.getInventory());
    }
}
