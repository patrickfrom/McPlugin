package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            World world = player.getWorld();
            Location spawnLocation = new Location(world, 201,91,-285);
            player.teleport(spawnLocation);
        }
        return true;
    }
}
