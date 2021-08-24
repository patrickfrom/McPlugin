package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

import java.sql.*;

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

        World world = player.getWorld();
        Location spawnLocation = new Location(world, 201,91,-285);
        player.teleport(spawnLocation);

        event.setJoinMessage(null);
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
        String query = "SELECT * FROM player WHERE PlayerUID='" + player.getUniqueId() +"';";
        Connection connection;

        Objective objective = scoreboard.registerNewObjective("ServerDisplay", "dummy", "MoneyDisplay");

        objective.setDisplayName("§0" + player.getName());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                Score info = objective.getScore("Money: " + resultSet.getString("Money"));
                info.setScore(1);
            }
            connection.close();
        } catch (SQLException ex) {
            player.sendMessage("EXCEPTION ServerEvents");
        }

        player.setScoreboard(scoreboard);
    }
}
