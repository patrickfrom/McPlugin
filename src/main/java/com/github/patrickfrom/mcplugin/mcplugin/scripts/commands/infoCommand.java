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
            stringBuilder.append("§1CREATORS\n");
            stringBuilder.append("§aNoah & Patrick\n");
            stringBuilder.append("§1∎∎∎∎∎∎∎∎∎∎∎∎∎∎\n");
            stringBuilder.append("§aVersion: 1.0.0\n");
            stringBuilder.append("§1∎∎∎∎∎∎∎∎∎∎∎∎∎∎");

            player.sendMessage(stringBuilder.toString());
        }
        return true;
    }
}
