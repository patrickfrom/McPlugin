package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerEvents implements Listener {
    private static final McPlugin plugin = McPlugin.getPlugin(McPlugin.class);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerListPing(ServerListPingEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("§6There are currently " + event.getNumPlayers() +  "/" + event.getMaxPlayers() + " players");
        stringBuilder.append("\n§cNoah & Patrick");

        event.setMotd(stringBuilder.toString());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Boolean hasPlayed = player.hasPlayedBefore();

        if(!hasPlayed)
            Bukkit.broadcastMessage("A new player has joined " + player.getName());

        TitleBar(player);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Bukkit.broadcastMessage("Goodbye " + player.getName() + " Hope to see you again!");
        event.setQuitMessage(null);
    }


    public void TitleBar(Player player) {
        BossBar bar = Bukkit.createBossBar("TEST", BarColor.BLUE, BarStyle.SOLID);
        bar.addPlayer(player);
    }
}
