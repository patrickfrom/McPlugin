package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerEvents implements Listener {
    private static final McPlugin plugin = McPlugin.getPlugin(McPlugin.class);

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage("");

        player.sendMessage("Welcome " + player.getName());
    }

    @EventHandler
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (entity.getType() == EntityType.VILLAGER) {
            if (entity.getCustomName().equals("Bob")) {
                player.sendMessage("Why'd you poke me?!");
            }
        }
    }

    public void CreateMenu(Player player) {
        Inventory main = player.getInventory();

        Utils.createItem(main, Material.GOLD_INGOT,1,1,"§8§lCREDITS");
        Utils.createItem(main, Material.BARRIER,1,2,"§4Exit Menu");
        Utils.createItem(main, Material.CLOCK,1,3,"§510 sec timer");
    }

    boolean jumpBoost = false;
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PotionEffect slowEffect = new PotionEffect(PotionEffectType.SLOW, 10, 3);
        PotionEffect jumpEffect = new PotionEffect(PotionEffectType.JUMP, 10, 5);
        PotionEffect speedEffect = new PotionEffect(PotionEffectType.SPEED, 10, 5);
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
            givePotionEffect(player, jumpEffect);
        } else if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.REDSTONE_BLOCK) {
            givePotionEffect(player, speedEffect);
        } else if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.COAL_BLOCK) {
            givePotionEffect(player, slowEffect);
        }
    }
    public void givePotionEffect(Player player, PotionEffect effect) {
        player.addPotionEffect(effect);
    }
}
