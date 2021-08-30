package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.ClickAction;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.CustomHolder;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Icon;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus.MainMenu;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Mineable;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Ore;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.Objects.Tool;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.*;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class PlayerEvents implements Listener {
    Random random = new Random();

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
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        Location spawnLocation = new Location(world, 197,200,-285);
        player.teleport(spawnLocation);
    }

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location spawnLocation = new Location(world, 197,200,-285);
        player.teleport(spawnLocation);
    }

    @EventHandler
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (entity.getType() == EntityType.VILLAGER) {
            if (entity.getCustomName().equals("Bob")) {
                MainMenu main = new MainMenu(player);
            } else if (entity.getCustomName().equals("Henry")) {
                int sellAmount = 0;
                int expAmount = 0;
                Inventory inventory = player.getInventory();
                for(Ore ore : Utils.ores) {
                    for (int i = 0; i <= 36; i++) {
                        int itemStackIndex = inventory.first(ore.getMaterial());
                        if (itemStackIndex != -1) {
                            ItemStack stack = inventory.getItem(itemStackIndex);
                            sellAmount += stack.getAmount() * ore.getSellPrice();
                            expAmount += 1 * stack.getAmount();
                            inventory.clear(itemStackIndex);
                        }
                    }
                }
                if (sellAmount != 0) {
                    addMoney(player, sellAmount);
                    ServerEvents.AddExperience(player, expAmount);
                }
            }
        }
    }

    @EventHandler
    public void OnEntityDamagedEvent(EntityDamageEvent event) {
        if(!event.getEntity().isOp()) {
            event.setCancelled(true);
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
    public void OnSmeltEvent(FurnaceSmeltEvent event) {
        ItemStack itemSource = event.getSource();
        Material itemMaterial = null;
        String rarity = null;
        int sellPrice = 0;

        for(Ingot ingot : Utils.ingots) {
            if(itemSource.getType() == ingot.getSmeltMaterial()) {
                itemMaterial = ingot.getMaterial();
                sellPrice = ingot.getSellPrice();
                rarity = ingot.getRandom();
            }
        }

        ItemStack smeltedItem = new ItemStack(Utils.createItem(itemMaterial, 1, rarity + " " + itemSource.getItemMeta().getDisplayName(), "Sell Price: " + sellPrice));
        event.setResult(smeltedItem);
    }

    @EventHandler
    public void FurnaceExtractEvent(FurnaceExtractEvent event) {
        Player player = event.getPlayer();
        Material itemType = event.getItemType();
        for(Ingot ingot : Utils.ingots) {
            if(itemType == ingot.getMaterial()) {
                removeMoney(player, ingot.getSellPrice()/2);
            }
        }
    }

    @EventHandler
    public void OnPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
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

        Location parkourLeft = new Location(world, 183, 194, -253);
        Location parkourRight = new Location(world, 130, 195, -296);

        BoundingBox parkourBox = new BoundingBox(
                parkourLeft.getX(),
                parkourLeft.getY(),
                parkourLeft.getZ(),
                parkourRight.getX(),
                parkourRight.getY(),
                parkourRight.getZ()
        );

        Collection<Entity> entities = world.getNearbyEntities(parkourBox);
        for (Entity entity : entities) {
            if(entity.getUniqueId() == player.getUniqueId()) {
                Location spawnLocation = new Location(world, 197, 200, -286);
                player.teleport(spawnLocation);
            }
        }

        ServerEvents.checkMineBorders(player);
    }

    @EventHandler
    public void OnItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Inventory inventory = player.getInventory();
        ItemStack item = player.getItemInHand();

        Action action = event.getAction();
        Block block = event.getClickedBlock();
        World world = block.getLocation().getWorld();

        Tool currentTool = null;
        Mineable currentMineable = null;
        boolean isDeepslate = false;

        int chance = 0;
        int randomNum = random.nextInt(100);

        if(action == Action.LEFT_CLICK_BLOCK) {
            for(Mineable mineable : Utils.mineables) {
                for(Tool tool : Utils.tools) {
                    if (item.getType() == tool.getPickaxe()) {
                        if (block.getType() == mineable.getSpecialBlock()) {
                            if(inventory.firstEmpty() == -1) {
                                player.sendMessage("Your inventory is full");
                            }
                            block.setType(mineable.getType());
                            isDeepslate = true;
                            currentTool = tool;
                            currentMineable = mineable;
                            chance = mineable.getChance();
                        } else if(block.getType() == mineable.getType()) {
                            isDeepslate = false;
                            currentTool = tool;
                            currentMineable = mineable;
                            chance = mineable.getChance();
                        }
                    }
                }
            }
            Ore ore = currentMineable.getOre();
            if(!isDeepslate) {
                inventory.addItem(Utils.createItem(ore.getMaterial(), currentTool.getGatherAmount(), ore.getDisplayName(), ore.getLore()));
            } else {
                inventory.addItem(Utils.createItem(ore.getMaterial(), currentTool.getGatherAmount() * 7, ore.getDisplayName(), ore.getLore()));
            }
            if(randomNum <= chance) {
                world.spawnParticle(Particle.EXPLOSION_LARGE, block.getLocation(), 100);
                world.playSound(block.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
                block.setType(currentMineable.getSpecialBlock());
            }
        } else if(action == Action.RIGHT_CLICK_BLOCK) {

        }
    }

    @EventHandler
    public void OnItemDamaged(BlockDamageEvent event) {
        Player player = event.getPlayer();
        if(!player.isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void OnBlockPlaced(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!player.isOp()) {
            event.setCancelled(true);
        }
    }

    public void givePotionEffect(Player player, PotionEffect effect) {
        player.addPotionEffect(effect);
    }

    public void addMoney(Player player, int amount) {
        String query = "UPDATE minecraft.player SET Money = Money + ? WHERE PlayerUID = ?;";
        Connection connection;
        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, player.getUniqueId().toString());

            preparedStatement.executeUpdate();

            ServerEvents.CreateScoreboard(player);
            connection.close();
        } catch (SQLException throwables) {
            Logger.getLogger("Huh, looks like the money didn't increase");
        }
    }

    public void removeMoney(Player player, int amount) {
        String query = "UPDATE minecraft.player SET Money = Money - ? WHERE PlayerUID = ?;";
        Connection connection;
        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, player.getUniqueId().toString());

            preparedStatement.executeUpdate();

            ServerEvents.CreateScoreboard(player);
            connection.close();
        } catch (SQLException throwables) {
            Logger.getLogger("Huh, looks like the money didn't decrease");
        }
    }
}
