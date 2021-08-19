package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEvents implements Listener {
    private static final McPlugin plugin = McPlugin.getPlugin(McPlugin.class);

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage("");

        player.sendMessage("Welcome " + player.getName());
    }

    @EventHandler
    public void PlayerEggThrowEvent(PlayerEggThrowEvent event) {
        Player player = event.getPlayer();
        player.chat("Hi");
        player.sendMessage("Hello");
    }


    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PotionEffect effect = PotionEffect(PotionEffectType.JUMP, 1, 5);

        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
            player.addPotionEffect(effect);
        }
    }
}
