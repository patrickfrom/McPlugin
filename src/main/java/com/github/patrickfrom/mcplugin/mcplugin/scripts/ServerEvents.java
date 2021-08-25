package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import sun.security.x509.UniqueIdentity;

import java.sql.*;
import java.util.UUID;

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

        if(!hasPlayed) {
            Bukkit.broadcastMessage("A new player has joined " + player.getName());
            player.getInventory().addItem(Utils.createItem(Material.FLINT, 1, "Flint Shard", "Your first mining tool"));
        }

        CreateExperienceBar(player);
        CreateScoreboard(player);

        World world = player.getWorld();
        Location spawnLocation = new Location(world, 197,200,-285);
        player.teleport(spawnLocation);

        event.setJoinMessage(null);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Bukkit.broadcastMessage("Goodbye " + player.getName() + " Hope to see you again!");
        event.setQuitMessage(null);
    }

    public static void AddExperience(Player player, int expAmount) {
        UUID id = player.getUniqueId();
        String query = "UPDATE minecraft.player SET exp = exp + "+expAmount+" WHERE PlayerUID = "+id+";";

        Connection connection;
        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            connection.close();
        } catch (SQLException ex) {
            player.sendMessage("EXCEPTION ServerEvents: "+ex);
        }

    }

    public void CreateExperienceBar(Player player) {
        UUID id = player.getUniqueId();
        String query = "SELECT Level, exp FROM player WHERE PlayerUID='" + id +"';";

        Connection connection;

        double progress = 0;
        double level = 0;
        int exp = 0;

        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                level = resultSet.getByte("Level");
                exp = resultSet.getInt("exp");
            }
            connection.close();
        } catch (SQLException ex) {
            player.sendMessage("EXCEPTION ServerEvents: "+ex);
        }

        // y = x^2
        // y = exp, x = level
        // exp / level^2
        // FIX MATH
        level = Math.sqrt(exp);
        int finalLevel = (int) level;

        progress = exp / Math.pow(finalLevel,2);

        BossBar bar = Bukkit.createBossBar("Level: "+level, BarColor.WHITE, BarStyle.SOLID);

        player.sendMessage("level is: "+finalLevel);
        player.sendMessage("exp is: "+exp);
        player.sendMessage("Progress is: "+progress);
        bar.setProgress(progress);
        bar.addPlayer(player);
    }

    public static void CreateScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        String query = "SELECT Money FROM player WHERE PlayerUID='" + player.getUniqueId() +"';";
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
