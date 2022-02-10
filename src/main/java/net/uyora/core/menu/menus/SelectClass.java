package net.uyora.core.menu.menus;

import net.uyora.core.Core;
import net.uyora.core.character.classes.ClassType;
import net.uyora.core.menu.ScrollMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectClass extends ScrollMenu {
    public SelectClass(Core main, String name) {
        super(main, name);
        populateClassMap();
    }

    private Map<Integer, ClassType> classMap = new HashMap<>();

    private List<Integer> confirmButton = Arrays.asList(48, 49, 50, 39, 40, 41);

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void execute(InventoryClickEvent e) {
        super.execute(e);
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        if (confirmButton.contains(e.getSlot())){
            if (main.getCharacterCreatorManager().hasCreator(p)) {
                main.getCharacterCreatorManager().getCharacterCreator(p).setClass(classMap.get(current));
                main.getCharacterCreatorManager().getCharacterCreator(p).openRaceSelector();
            } else {
                p.sendMessage(main.getStringUtilities().TCC("&4I don't know how you opened this menu, but you are currently not creating a character so this did nothing!"));
            }
        }


    }

    @Override
    public void populateScrollMap() {
        int x= 0;
        for (ClassType type : ClassType.values()){
            x++;
            ItemStack item = new ItemStack(Material.BLAZE_ROD);
            ItemMeta im = item.getItemMeta();
            im.setCustomModelData(type.getModelData());
            im.setDisplayName(ChatColor.GREEN + type.toString());
            item.setItemMeta(im);
            scrollMap.put(x, item);

        }
    }

    public void populateClassMap() {
        int x= 0;
        for (ClassType type : ClassType.values()){
            x++;

            classMap.put(x, type);

        }
    }

    @Override
    public void populateGreyScrollMap() {
        int x= 0;
        for (ClassType type : ClassType.values()){
            x++;
            ItemStack item = new ItemStack(Material.BLAZE_ROD);
            ItemMeta im = item.getItemMeta();
            im.setCustomModelData(type.getGreyData());
            im.setDisplayName(ChatColor.GRAY + type.toString());
            item.setItemMeta(im);
            greyMap.put(x, item);

        }
    }
}
