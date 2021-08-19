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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

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

        CreateTitleBar(player);
        CreateScoreboard(player);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Bukkit.broadcastMessage("Goodbye " + player.getName() + " Hope to see you again!");
        event.setQuitMessage(null);
    }


    public void CreateTitleBar(Player player) {
        BossBar bar = Bukkit.createBossBar("§0Sigma Chad", BarColor.WHITE, BarStyle.SOLID);
        bar.addPlayer(player);
    }

    public void CreateScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Display", "dummy", "Sigma");

        objective.setDisplayName("§0Sigma");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score info = objective.getScore("§f" + player.getName());
        info.setScore(1);

        player.setScoreboard(scoreboard);
    }
}
