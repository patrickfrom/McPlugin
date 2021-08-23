package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI;

import com.github.patrickfrom.mcplugin.mcplugin.scripts.DataManager;
import com.github.patrickfrom.mcplugin.mcplugin.scripts.ServerEvents;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.*;
import java.util.logging.Logger;

public class ShopUtils {

    public static void Buy(Player player, ItemStack item, int price) {
        String query = "UPDATE minecraft.player SET Money = Money - ? WHERE PlayerUID = ? AND Money >= ?";
        String queryMoney = "SELECT * FROM minecraft.player WHERE PlayerUID='" + player.getUniqueId() + "';";

        try(Connection connection = DriverManager.getConnection(DataManager.url, DataManager.user, DataManager.password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryMoney);

            int result = 0;
            while (resultSet.next()) {
                result = resultSet.getInt("Money");
            }
            if(result >= price) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, price);
                preparedStatement.setString(2, player.getUniqueId().toString());
                preparedStatement.setInt(3, price);

                preparedStatement.executeUpdate();

                player.getInventory().addItem(item);
                ServerEvents.CreateScoreboard(player);
            } else {
                player.sendMessage("You don't have enough money to buy the item " + item.getItemMeta().getDisplayName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.toString());
        }
    }
}
