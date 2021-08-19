package com.github.patrickfrom.mcplugin.mcplugin;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.PlayerEvents;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.ServerEvents;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Server server = getServer();
        server.getPluginManager().registerEvents(new PlayerEvents(), this);
        server.getPluginManager().registerEvents(new ServerEvents(), this);
    }
    @Override
    public void onDisable() {
    }
}
