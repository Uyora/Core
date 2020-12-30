package uyora.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.*;
import uyora.core.Main;
import uyora.core.menu.AbstractMenu;


public class MenuListener implements Listener {

	@EventHandler
	public void listen(EnchantItemEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(InventoryCloseEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(InventoryClickEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(InventoryDragEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(TradeSelectEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(InventoryOpenEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(PrepareAnvilEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(PrepareItemCraftEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(PrepareItemEnchantEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

	@EventHandler
	public void listen(PrepareSmithingEvent event) {
		AbstractMenu menu = Main.core.getMenuManager().getMenu(event.getInventory());
		if(menu == null)
			return;
		menu.listen(event);
	}

}
