package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemMenu {
    CustomHolder menu = new CustomHolder(54, "§1Buy Items");
    ItemStack barrier = Utils.createItem(Material.BARRIER, 1, "§2Exit Item Shop");
    ItemStack wooden = Utils.createItem(Material.WOODEN_PICKAXE, 1, "§7Wooden Pickaxe", "Price: 250");
    ItemStack stone = Utils.createItem(Material.STONE_PICKAXE, 1, "§8Stone Pickaxe", "Price: 750");
    ItemStack iron = Utils.createItem(Material.IRON_PICKAXE, 1, "§fIron Pickaxe", "Price: 2000");
    ItemStack gold = Utils.createItem(Material.GOLDEN_PICKAXE, 1, "§6Gold Pickaxe", "Price: 4500");
    ItemStack diamond = Utils.createItem(Material.DIAMOND_PICKAXE, 1, "§1Diamond Pickaxe", "Price: 10000");
    ItemStack netherite = Utils.createItem(Material.NETHERITE_PICKAXE, 1, "§l§fNetherite Pickaxe", "Price: 30000");
    ItemStack stonecutter = Utils.createItem(Material.STONECUTTER, 1, "§kStonecutter", "Price: 500000");

    ItemStack potato = Utils.createItem(Material.PORKCHOP,1,"Potato", "Price: 5");
    ItemStack apple = Utils.createItem(Material.APPLE,1,"Apple", "Price: 5");
    ItemStack pork = Utils.createItem(Material.PORKCHOP,1,"Porkchop", "Price: 10");
    ItemStack beef = Utils.createItem(Material.BEEF,1,"Beef", "Price: 10");
    ItemStack bread = Utils.createItem(Material.BREAD,1,"Bread", "Price: 25");
    ItemStack cookedCod = Utils.createItem(Material.COOKED_COD,1,"Cooked Cod", "Price: 50");
    ItemStack cookedSalmon = Utils.createItem(Material.COOKED_SALMON,1,"Cooked Salmon", "Price: 50");
    ItemStack steak = Utils.createItem(Material.COOKED_BEEF,1,"Steak", "Price: 50");
    ItemStack cookedPorkchop = Utils.createItem(Material.COOKED_PORKCHOP,1,"Cooked Porkchop", "Price: 50");
    ItemStack goldenCarrot = Utils.createItem(Material.GOLDEN_CARROT,1,"Golden Carrot", "Price: 500");


    Icon IPotato = new Icon(potato).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,apple,5);
    });
    Icon IApple = new Icon(apple).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,apple,5);
    });
    Icon IPork = new Icon(pork).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,pork,10);
    });
    Icon IBeef = new Icon(beef).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,beef,10);
    });
    Icon IBread = new Icon(bread).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,bread,25);
    });
    Icon ICod = new Icon(cookedCod).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,cookedCod,50);
    });
    Icon ISalmon = new Icon(cookedSalmon).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,cookedSalmon,50);
    });
    Icon ISteak = new Icon(steak).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,steak,50);
    });
    Icon IPorkchop = new Icon(cookedPorkchop).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,cookedPorkchop,50);
    });
    Icon IGoldenCarrot = new Icon(goldenCarrot).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,goldenCarrot,500);
    });


    Icon Exit = new Icon(barrier).addClickAction(player -> {
       ShopMenu shopMenu = new ShopMenu(player);
    });

    Icon WoodenPickaxe = new Icon(wooden).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,wooden,250);
    });

    Icon IronPickaxe = new Icon(iron).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,iron,2000);
    });

    Icon StonePickaxe = new Icon(stone).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,stone,750);
    });

    Icon GoldPickaxe = new Icon(gold).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player,gold,4500);
    });

    Icon DiamondPickaxe = new Icon(diamond).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player, diamond,10000);
    });

    Icon NetheritePickaxe = new Icon(netherite).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player, netherite,30000);
    });

    Icon StoneCutter = new Icon(stonecutter).addClickAction(player -> {
        BuyMenu buyMenu = new BuyMenu(player, stonecutter,500000);
    });

    public ItemMenu(Player player) {
        menu.setIcon(49,Exit);
        menu.setIcon(0,WoodenPickaxe);
        menu.setIcon(1,StonePickaxe);
        menu.setIcon(2,IronPickaxe);
        menu.setIcon(3,GoldPickaxe);
        menu.setIcon(4,DiamondPickaxe);
        menu.setIcon(5,NetheritePickaxe);
        menu.setIcon(6,StoneCutter);

        menu.setIcon(9,IPotato);
        menu.setIcon(10,IApple);
        menu.setIcon(11,IPork);
        menu.setIcon(12,IBeef);
        menu.setIcon(13,IBread);
        menu.setIcon(14,ISalmon);
        menu.setIcon(15,ICod);
        menu.setIcon(16,ISteak);
        menu.setIcon(17,IPorkchop);
        menu.setIcon(18,IGoldenCarrot);

        player.openInventory(menu.getInventory());
    }
}
