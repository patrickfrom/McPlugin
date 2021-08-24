package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.ShopUtils;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BuyMenu {
    CustomHolder menu = new CustomHolder(54,"Buy Item");
    ItemStack barrier = Utils.createItem(Material.BARRIER, 1, "§4Exit Item Shop");
    ItemStack lime = Utils.createItem(Material.LIME_CONCRETE,1,"+1");
    ItemStack red = Utils.createItem(Material.RED_CONCRETE,1,"-1");
    ItemStack emerald = Utils.createItem(Material.EMERALD_BLOCK,1,"§2Buy Item(s)");

    ItemStack item;
    int price;

    Icon Exit = new Icon(barrier).addClickAction(player -> {
        ItemMenu itemMenu = new ItemMenu(player);
    });

    Icon more = new Icon(lime).addClickAction(player -> {
        int currentAmount = this.item.getAmount();
        if (currentAmount == 64) {
            return;
        } else {
            this.item.setAmount(currentAmount+1);
            Icon newIcon = new Icon(this.item);
            menu.setIcon(22, newIcon);
            player.openInventory(menu.getInventory());
        }
    });

    Icon less = new Icon(red).addClickAction(player -> {
        int currentAmount = menu.getInventory().getItem(22).getAmount();
        if (currentAmount == 1) {
            return;
        } else {
            this.item.setAmount(currentAmount-1);
            Icon newIcon = new Icon(this.item);
            menu.setIcon(22, newIcon);
            player.openInventory(menu.getInventory());
        }
    });

    Icon buyItem = new Icon(emerald).addClickAction(player -> {
        int price = this.price * this.item.getAmount();
        ShopUtils.Buy(player,this.item,price);
        ItemMenu itemMenu = new ItemMenu(player);
    });

    public BuyMenu(Player player, ItemStack item, int price) {
        this.item = item;
        this.price = price;
        Icon itemIcon = new Icon(this.item).addClickAction(playerClick -> {
        });
        menu.setIcon(22, itemIcon);

        menu.setIcon(49, Exit);
        menu.setIcon(19, more);
        menu.setIcon(25, less);
        menu.setIcon(40, buyItem);

        player.openInventory(menu.getInventory());
    }
}
