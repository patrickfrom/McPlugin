package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.ClickAction;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus.MainMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class PlayerEvents implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String query = "INSERT INTO minecraft.player(PlayerUID, Money) VALUES('" + player.getUniqueId() + "', '" + 150 + "');";
        Connection connection;
        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            Logger.getLogger("Already exists");
        }
    }

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location spawnLocation = new Location(world, 201,91,-285);
        player.teleport(spawnLocation);
    }

    @EventHandler
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (entity.getType() == EntityType.VILLAGER) {
            if (entity.getCustomName().equals("Bob")) {
                MainMenu main = new MainMenu(player);
            }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof CustomHolder) {
            event.setCancelled(true);

            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();

                ItemStack itemStack = event.getCurrentItem();
                if (itemStack == null || itemStack.getType() == Material.AIR) return;

                CustomHolder customHolder = (CustomHolder) event.getView().getTopInventory().getHolder();

                Icon icon = customHolder.getIcon(event.getRawSlot());
                if (icon == null) return;

                for (ClickAction clickAction : icon.getClickActions()) {
                    clickAction.execute(player);
                }
            }
        }
    }

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
        } else if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK) {
            givePotionEffect(player, speedEffect);
            givePotionEffect(player, jumpEffect);
        }
    }

    @EventHandler
    public void OnItemHit(BlockDamageEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();

        ItemStack item = player.getItemInHand();
        Block block = event.getBlock();

        for(Entry<Material, Material> mineable : Utils.mineables.entrySet()) {
            for(Entry<Material, Integer> tool : Utils.tools.entrySet()) {
                if(item.getType() == tool.getKey()) {
                    if (block.getType() == mineable.getKey()) {
                        inventory.addItem(Utils.createItem(mineable.getValue(), 1 * tool.getValue(), mineable.getValue().name()));
                    }
                }
            }
        }
    }

    public void givePotionEffect(Player player, PotionEffect effect) {
        player.addPotionEffect(effect);
    }
}
