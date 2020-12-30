package uyora.core.menu;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

	private final Map<Inventory, AbstractMenu> menus = new HashMap<>();

	public void registerMenu(AbstractMenu menu) {
		menus.put(menu.getInventory(), menu);
	}

	public void unregisterMenu(AbstractMenu menu) {
		menus.remove(menu.getInventory());
	}

	public AbstractMenu getMenu(Inventory inventory) {
		return menus.get(inventory);
	}
}
