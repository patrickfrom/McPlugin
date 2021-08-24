package com.github.patrickfrom.mcplugin.mcplugin.scripts.GUI;

import org.bukkit.inventory.ItemStack;
import java.util.List;
import java.util.ArrayList;

public class Icon {
    public final ItemStack itemStack;

    public final List<ClickAction> clickActions = new ArrayList<>();

    public Icon(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public Icon addClickAction(ClickAction clickAction) {
        this.clickActions.add(clickAction);
        return this;
    }

    public List<ClickAction> getClickActions() {
        return this.clickActions;
    }
}
