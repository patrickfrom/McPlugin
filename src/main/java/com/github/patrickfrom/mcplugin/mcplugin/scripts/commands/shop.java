package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus.ShopMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class shop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            ShopMenu shopMenu = new ShopMenu(player);
        }
        return true;
    }
}
