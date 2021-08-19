package com.github.patrickfrom.mcplugin.mcplugin;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.Events;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Server server = getServer();
        server.getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
    }
}
