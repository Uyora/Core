package uyora.core.menu.template;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import uyora.core.menu.AbstractMenu;

import java.util.ArrayList;
import java.util.List;

public abstract class ScrollMenu extends AbstractMenu {

	private final List<Item> entry = new ArrayList<>();

	protected int nextDisplay;
	protected int middleDisplay;
	protected int prevDisplay;

	private int[] nextButton;
	private int[] middleButton;
	private int[] prevButton;
	private int current = 0;

	public ScrollMenu(String title) {
		super(6, title);
	}

	@Override
	public void openMenu(Player player) {
		super.openMenu(player);
		setDisplay(current);
	}

	@Override
	public void listen(InventoryClickEvent event) {
		event.setCancelled(true);

		int slot = event.getSlot();
		if(checkMiddleButton(slot)) {
			onSelect(entry.get(current), event);
		}else if(checkNextButton(slot)) {
			if(current == entry.size() - 1)
				return;
			setDisplay(++current);
		}else if(checkPrevButton(slot)) {
			if(current == 0)
				return;
			setDisplay(--current);
		}
	}

	public void addItem(Item item) {
		entry.add(item);
	}

	public void setNextButton(int... slots) {
		nextButton = slots;
	}

	public void setMiddleButton(int... slots) {
		middleButton = slots;
	}

	public void setPrevButton(int... slots) {
		prevButton = slots;
	}

	public abstract void onSelect(Item selected, InventoryClickEvent event);

	private void setDisplay(int id) {
		Item middle = entry.get(id);
		inventory.setItem(middleDisplay, middle.getFocus());
		if(id + 1 < entry.size()) {
			Item next = entry.get(id + 1);
			inventory.setItem(nextDisplay, next.getSide());
		}else
			inventory.setItem(nextDisplay, null);
		if(id - 1 >= 0) {
			Item prev = entry.get(id - 1);
			inventory.setItem(prevDisplay, prev.getSide());
		}else
			inventory.setItem(prevDisplay, null);
	}

	private boolean checkNextButton(int slot) {
		for(int i : nextButton) {
			if (i == slot) {
				return true;
			}
		}
		return false;
	}

	private boolean checkMiddleButton(int slot) {
		for(int i : middleButton) {
			if (i == slot) {
				return true;
			}
		}
		return false;
	}

	private boolean checkPrevButton(int slot) {
		for(int i : prevButton) {
			if (i == slot) {
				return true;
			}
		}
		return false;
	}

	public interface Item {
		ItemStack getFocus();
		ItemStack getSide();
	};

}
