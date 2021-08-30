package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CustomHolder implements InventoryHolder {

    private final Map<Integer, Icon> icons = new HashMap<>();

    private int size;
    private InventoryType inventoryType;
    private final String title;

    public CustomHolder(int size, String title) {
        this.size = size;
        this.title = title;
    }

    public CustomHolder(InventoryType inventoryType, String title) {
        this.inventoryType = inventoryType;
        this.title = title;
    }

    public void setIcon(int position, Icon icon) {
        this.icons.put(position, icon);
    }

    public Icon getIcon(int position) {
        return this.icons.get(position);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, this.size, this.title);

        for (Entry<Integer, Icon> entry : this.icons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }

        return inventory;
    }

    public Inventory getInventoryWithType() {
        Inventory inventory = Bukkit.createInventory(this, this.inventoryType, this.title);

        for (Entry<Integer, Icon> entry : this.icons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }

        return inventory;
    }
}
