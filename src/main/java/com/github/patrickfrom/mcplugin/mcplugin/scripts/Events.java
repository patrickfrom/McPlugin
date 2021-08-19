package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
    private static final McPlugin plugin = McPlugin.getPlugin(McPlugin.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Hello");
    }
}
