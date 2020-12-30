package uyora.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;

public abstract class AbstractMenu {

	protected Inventory inventory;

	public AbstractMenu(InventoryType type, String title) {
		this(Bukkit.createInventory(null, type, title));
	}

	public AbstractMenu(int row, String title) {
		this(Bukkit.createInventory(null, Math.max(Math.min(row, 6), 1) * 9, title));
	}

	public AbstractMenu(Inventory inventory) {
		this.inventory = inventory;
		Main.core.getMenuManager().registerMenu(this);
	}

	public AbstractMenu() {

	}

	public void openMenu(Player player) {
		player.openInventory(inventory);
	}

	public Inventory getInventory() {
		return inventory;
	}

	// Events
	public void listen(EnchantItemEvent event) {

	}

	public void listen(InventoryCloseEvent event) {
		Main.core.getMenuManager().unregisterMenu(this);
	}

	public void listen(InventoryClickEvent event) {

	}

	public void listen(InventoryDragEvent event) {

	}

	public void listen(TradeSelectEvent event) {

	}

	public void listen(InventoryOpenEvent event) {

	}

	public void listen(PrepareAnvilEvent event) {

	}

	public void listen(PrepareItemCraftEvent event) {

	}

	public void listen(PrepareItemEnchantEvent event) {

	}

	public void listen(PrepareSmithingEvent event) {

	}

}
