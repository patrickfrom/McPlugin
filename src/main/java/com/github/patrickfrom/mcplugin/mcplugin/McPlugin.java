package com.github.patrickfrom.mcplugin.mcplugin;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.PlayerEvents;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.ServerEvents;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.commands.*;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Server server = getServer();
        server.getPluginManager().registerEvents(new PlayerEvents(), this);
        server.getPluginManager().registerEvents(new ServerEvents(), this);

        this.getCommand("info").setExecutor(new infoCommand());
        this.getCommand("entities").setExecutor(new entitiesInArea());
        this.getCommand("spawn").setExecutor(new spawn());
        this.getCommand("shop").setExecutor(new shop());
        this.getCommand("menu").setExecutor(new menu());
    }
    @Override
    public void onDisable() {
    }
}
