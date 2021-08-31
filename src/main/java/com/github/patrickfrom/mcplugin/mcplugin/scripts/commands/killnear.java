package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.Collection;

public class killnear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            World world = player.getWorld();
            Location location = player.getLocation();
            BoundingBox box = new BoundingBox(location.getX()-5, location.getY()-5, location.getZ()-5, location.getX()+5, location.getY()+5, location.getZ()+5);
            Collection<Entity> entities = world.getNearbyEntities(box);

            for(Entity entity : entities) {
                Damageable damage = (Damageable) entity;
                damage.damage(1000000);
                return true;
            }
        }
        return true;
    }
}
