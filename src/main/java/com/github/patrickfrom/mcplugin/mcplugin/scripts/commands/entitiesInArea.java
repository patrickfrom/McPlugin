package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.entity.Entity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class entitiesInArea implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();

            Map<String, Integer> entities = new HashMap<String, Integer>();

            BoundingBox Box = new BoundingBox(location.getBlockX() + 50, location.getBlockY() + 50, location.getBlockZ() + 50, location.getBlockX() - 50, location.getBlockY() - 50, location.getBlockZ() - 50);

            Collection <Entity> entityList = location.getWorld().getNearbyEntities(Box);

            for(Entity entity : entityList) {
                if(entities.containsKey(entity.getName())) {
                    entities.replace(entity.getName(),  entities.get(entity.getName()) + 1);
                } else {
                    entities.put(entity.getName(), 1);
                }   
            }

            for(Entry<String, Integer> entry : entities.entrySet()) {
                player.sendMessage(entry.getKey() + ": " + entry.getValue());
            }

            entities.clear();
        }
        return true;
    }
}