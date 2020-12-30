package uyora.core.menu.charactercreation;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uyora.core.character.CharacterData;
import uyora.core.character.UyoraCharacter;
import uyora.core.menu.template.ScrollMenu;


public class ClassSelectorMenu extends ScrollMenu {

	private final UyoraCharacter character;
	private final String name;

	public ClassSelectorMenu(UyoraCharacter character, String name) {
		super("Choose your class:");

		this.character = character;
		this.name = name;

		setNextButton(15, 16, 17, 24, 25, 26, 33, 34, 35);
		setMiddleButton(21, 22, 23, 30, 31, 32, 39, 40, 41);
		setPrevButton(9, 10, 11, 18, 19, 20, 27, 28, 29);

		nextDisplay = 7;
		middleDisplay = 13;
		prevDisplay = 1;

		addItem(new ClassItem(CharacterData.Class.WARRIOR, 7, 17));
		addItem(new ClassItem(CharacterData.Class.MAGE, 5, 15));
		addItem(new ClassItem(CharacterData.Class.DRUID, 3, 13));
		addItem(new ClassItem(CharacterData.Class.HUNTER, 4, 14));
		addItem(new ClassItem(CharacterData.Class.ROGUE, 1, 11));
		addItem(new ClassItem(CharacterData.Class.PRIEST, 2, 12));
		addItem(new ClassItem(CharacterData.Class.PALADIN, 6, 16));

	}

	@Override
	public void onSelect(Item selected, InventoryClickEvent event) {
		character.createCharacter(name, "none", ((ClassItem) selected).clazz);
		event.getWhoClicked().closeInventory();
	}

	static class ClassItem implements Item {

		private final CharacterData.Class clazz;

		private final ItemStack item = new ItemStack(Material.BLAZE_ROD);
		private final ItemMeta meta;
		private final int focus, side;

		protected ClassItem(CharacterData.Class clazz, int focusId, int sideId) {
			this.clazz = clazz;
			meta = item.getItemMeta();
			focus = focusId;
			side = sideId;
		}

		@Override
		public ItemStack getFocus() {
			meta.setCustomModelData(focus);
			meta.setDisplayName(ChatColor.DARK_GREEN + clazz.name());
			item.setItemMeta(meta);
			return item;
		}

		@Override
		public ItemStack getSide() {
			meta.setCustomModelData(side);
			meta.setDisplayName(ChatColor.DARK_GRAY + clazz.name());
			item.setItemMeta(meta);
			return item;
		}

	}

}
