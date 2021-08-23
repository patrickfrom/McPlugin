package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI.Menus.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class menu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            MainMenu mainMenu = new MainMenu(player);
        }
        return true;
    }
}
