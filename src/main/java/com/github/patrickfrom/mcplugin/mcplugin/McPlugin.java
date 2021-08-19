package com.github.patrickfrom.mcplugin.mcplugin;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.Events;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Events(), this);
        getLogger().info("MCPlugin - Noah & Patrick");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
