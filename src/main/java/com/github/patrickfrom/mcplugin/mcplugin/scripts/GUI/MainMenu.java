package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainMenu {
    CustomHolder menu = new CustomHolder(9, "Main");
    ItemStack barrier = new ItemStack(Material.BARRIER);
    ItemStack note = new ItemStack(Material.NOTE_BLOCK);
    ItemStack gold = new ItemStack(Material.GOLD_INGOT);
    ItemStack bed = new ItemStack(Material.RED_BED);

    Icon exitButton = new Icon(barrier).addClickAction(new ClickAction() {
        @Override
        public void execute(Player player) {
            player.closeInventory();
        }
    });

    Icon shop = new Icon(note).addClickAction(new ClickAction() {
        @Override
        public void execute(Player player) {

        }
    });

    Icon credits = new Icon(gold).addClickAction(new ClickAction() {
        @Override
        public void execute(Player player) {

        }
    });

    Icon spawn = new Icon(bed).addClickAction(new ClickAction() {
        @Override
        public void execute(Player player) {

        }
    });

    public MainMenu(Player player) {
        menu.setIcon(3,exitButton);
        menu.setIcon(1,shop);
        menu.setIcon(0,credits);
        menu.setIcon(2,spawn);

        player.openInventory(menu.getInventory());
    }
}
