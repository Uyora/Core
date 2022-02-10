package net.uyora.core.menu.menus;

import net.uyora.core.Core;
import net.uyora.core.menu.Menu;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


public class BagMenu extends Menu {
    public BagMenu(Core main, int size, String name) {
        super(main,  size, name);

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
    public  void execute(InventoryClickEvent e){
        super.execute(e);
        e.getWhoClicked().sendMessage("dbug");
    }
}
