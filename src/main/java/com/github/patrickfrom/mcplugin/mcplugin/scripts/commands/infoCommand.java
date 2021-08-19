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
            stringBuilder.append("∎∎∎∎∎∎∎∎∎∎∎∎∎∎\n");
            stringBuilder.append("  CREATORS\n");
            stringBuilder.append("  Noah & Patrick\n");
            stringBuilder.append("∎∎∎∎∎∎∎∎∎∎∎∎∎∎\n");
            stringBuilder.append("  Version: 1.0.0\n");
            stringBuilder.append("∎∎∎∎∎∎∎∎∎∎∎∎∎∎");

            player.sendMessage(stringBuilder.toString());
        }
        return true;
    }
}
