package net.uyora.core.menu;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public abstract class Menu {
    protected Inventory inventory;
    private MenuSession session;
    protected Core main;


    public Menu(Core main, int size, String name) {
        this.session = session;
        inventory = Bukkit.getServer().createInventory(null, size, ChatColor.translateAlternateColorCodes('&', name));
        this.main = main;
    }

    public Inventory getInventory() {
        return inventory;

    }

    public abstract void onClose(InventoryCloseEvent event);

    public abstract boolean hasParent();

    public abstract Menu getParent();

    public void execute(InventoryClickEvent e) {


    }
}
