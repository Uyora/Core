package uyora.core.menu.charactercreation;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uyora.core.Main;
import uyora.core.character.UyoraCharacter;
import uyora.core.menu.AbstractMenu;


public class NameMenu extends AbstractMenu {

	private final UyoraCharacter character;
	private String name = null;

	public NameMenu(UyoraCharacter character) {
		this.character = character;
	}

	@Override
	public void openMenu(Player player) {
		new AnvilGUI.Builder()
				.onClose(this::onClose)
				.onComplete(this::onComplete)
				.text("Enter a name")
				.itemLeft(new ItemStack(Material.NAME_TAG))
				.title(ChatColor.DARK_GREEN + "What's your name?")
				.plugin(Main.core)
				.open(player);
	}

	private void onClose(Player player) {
		if(name == null)
			return;
		ClassSelectorMenu classSelectorMenu = new ClassSelectorMenu(character, name);
		classSelectorMenu.openMenu(player);
	}

	private AnvilGUI.Response onComplete(Player player, String text) {
		if (text.length() <= 20) {
			name = text;
			return AnvilGUI.Response.close();
		}
		return AnvilGUI.Response.text("Invalid Name! Name is in use or exceeds 12 characters.");
	}

}
