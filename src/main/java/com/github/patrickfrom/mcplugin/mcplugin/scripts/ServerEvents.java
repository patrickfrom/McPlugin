package com.github.patrickfrom.mcplugin.mcplugin.scripts;

import com.github.patrickfrom.mcplugin.mcplugin.McPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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
import org.bukkit.util.BoundingBox;
import sun.security.x509.UniqueIdentity;

import java.sql.*;
import java.util.*;

public class ServerEvents implements Listener {
    public static List<BossBar> bossbarList = new ArrayList<BossBar>();

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

    public  static int[] getExperienceLevel(Player player) {
        UUID id = player.getUniqueId();
        String query = "SELECT exp FROM player WHERE PlayerUID='" + id +"';";

        Connection connection;
        int[] returnInt = new int[2];
        int exp = 0;

        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                exp = resultSet.getInt("exp");
            }
            connection.close();
        } catch (SQLException ex) {
            player.sendMessage("EXCEPTION ServerEvents: "+ex);
        }
        double level = Math.cbrt(exp);
        int roundedLevel = (int) Math.floor(level);

        returnInt[0] = exp;
        returnInt[1] = roundedLevel;
        return returnInt;
    }

    public static void AddExperience(Player player, int expAmount) {
        UUID id = player.getUniqueId();

        String query = "UPDATE player SET exp = exp + "+expAmount+" WHERE PlayerUID = '"+id+"';";

        Connection connection;
        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            player.sendMessage("EXCEPTION ServerEvents: "+ex);
        }
        CreateExperienceBar(player);
    }

    public static void CreateExperienceBar(Player player) {
        for(BossBar bossbar : bossbarList) {
            bossbar.removePlayer(player);
        }

        UUID id = player.getUniqueId();
        String query = "SELECT exp FROM player WHERE PlayerUID='" + id +"';";

        Connection connection;

        double level = 0;
        int exp = 0;

        try {
            connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                exp = resultSet.getInt("exp");
            }
            connection.close();
        } catch (SQLException ex) {
            player.sendMessage("EXCEPTION ServerEvents: "+ex);
        }

        level = Math.cbrt(exp);
        int roundedLevel = (int) Math.floor(level);
        double progress = level - roundedLevel;

        BossBar bar = Bukkit.createBossBar("Level: "+roundedLevel, BarColor.WHITE, BarStyle.SOLID);
        bossbarList.add(bar);

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

    public static void checkMineBorders(Player player) {
        World world = player.getWorld();

        Location copperMineBottom = new Location(world, 242, 198, -272);
        Location copperMineTop = new Location(world, 244, 201, -267);

        Location ironMineBottom = new Location(world, 271, 190, -269);
        Location ironMineTop = new Location(world, 272, 192, -265);

        Map<Integer, BoundingBox> mines = new HashMap<Integer, BoundingBox>() {{
           put(25, new BoundingBox(
                   copperMineBottom.getX(),
                   copperMineBottom.getY(),
                   copperMineBottom.getZ(),
                   copperMineTop.getX(),
                   copperMineTop.getY(),
                   copperMineTop.getZ()
           ));
           put(50, new BoundingBox(
                ironMineBottom.getX(),
                ironMineBottom.getY(),
                ironMineBottom.getZ(),
                ironMineTop.getX(),
                ironMineTop.getY(),
                ironMineTop.getZ()
           ));
        }};

        for(Map.Entry<Integer, BoundingBox> mine : mines.entrySet()) {
            Collection<Entity> entities = world.getNearbyEntities(mine.getValue());
            for (Entity entity : entities) {
                if (entity.getUniqueId() == player.getUniqueId()) {
                    if(getExperienceLevel(player)[1] < mine.getKey()) {
                        Location back = new Location(world, mine.getValue().getCenterX() - 3, mine.getValue().getCenterY(), mine.getValue().getCenterZ(), -90, 0);
                        player.sendMessage("You aren't the required level to access this mine!");
                        player.teleport(back);
                    }
                }
            }
        }
    }
}
