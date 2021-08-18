package com.github.patrickfrom.mcplugin.mcplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player;

public final class McPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void PlayerJoinEvent(Player playerJoined, String joinMessage) {

    }

    public void setJoinMessage() {

    }



}
