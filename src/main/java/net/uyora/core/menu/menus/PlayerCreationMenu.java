package net.uyora.core.menu.menus;

import net.uyora.core.Core;
import net.uyora.core.menu.Menu;
import net.uyora.core.usermanager.UyoraUser;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerCreationMenu extends Menu {


    public PlayerCreationMenu(Core main, int size, String name) {
        super(main,  9, name);
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }

    @Override
    public void execute(InventoryClickEvent e) {
        super.execute(e);
        Player player = (Player) e.getWhoClicked();
        UyoraUser user = main.getUyoraUserManager().getUser(player);

        (new AnvilGUI.Builder()).onClose((p) -> {
        }).onComplete((p, text) -> {
            if (text.length() <= 12 && !Bukkit.getOnlinePlayers().contains(text)) {
                player.setDisplayName(text);
                player.setPlayerListName(text);
                player.setCustomName(text);
                player.setCustomNameVisible(true);

                player.getInventory().setItem(8, new ItemStack(Material.SADDLE));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                return AnvilGUI.Response.close();
            } else {
                return AnvilGUI.Response.text("Invalid Name! Name is in use or exceeds 12 characters.");
            }
        })
                .text("Select a name")
                .item(new ItemStack(Material.NAME_TAG))
                .title(ChatColor.DARK_GREEN + "What's your name?")
                .plugin(main)
                .open(player);
    }
}
