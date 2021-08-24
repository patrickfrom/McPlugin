package com.github.patrickfrom.mcplugin.mcplugin.scripts.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class infoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("§4CREATORS\n");
            stringBuilder.append("§cNoah & Patrick\n");
            stringBuilder.append("§4∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎\n");
            stringBuilder.append("§cVersion: 1.0.0\n");
            stringBuilder.append("§4∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎");

            player.sendMessage(stringBuilder.toString());
        }
        return true;
    }
}
