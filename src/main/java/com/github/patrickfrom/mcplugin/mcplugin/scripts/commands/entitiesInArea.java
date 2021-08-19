package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.entity.Entity;
import java.util.Collection;

public class entitiesInArea implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            int[] loc1 = new int[3];
            int[] loc2 = new int[3];

            loc1[0] = location.getBlockX() + 50;
            loc1[1] = location.getBlockY() + 50;
            loc1[2] = location.getBlockZ() + 50;
            loc2[0] = location.getBlockX() - 50;
            loc2[1] = location.getBlockY() - 50;
            loc2[2] = location.getBlockZ() - 50;

            BoundingBox Box = new BoundingBox(loc1[0], loc1[1], loc1 [2], loc2[0], loc2[1], loc2[2]);

            if (args.length == 1) {
                Collection <Entity> entityList = location.getWorld().getNearbyEntities(Box);
            } else {
                Collection <Entity> entityList = location.getWorld().getNearbyEntities(Box);
            }

            /*for(Entity i : entityList) {
                player.sendMessage(i.getType().getName());
            }*/
        }
        return true;
    }
}