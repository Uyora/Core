package net.uyora.core.menu.menus;

import net.uyora.core.Core;
import net.uyora.core.character.races.RaceType;
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

public class SelectRace extends ScrollMenu {
    public SelectRace(Core main, String name) {
        super(main,  name);
        populateRaceMap();
    }


    private Map<Integer, RaceType> raceMap = new HashMap<>();

    private List<Integer> confirmButton = Arrays.asList(48, 49, 50, 39, 40, 41);

    @Override
    public void onClose(InventoryCloseEvent event) {

    }
    @Override
    public void execute(InventoryClickEvent e) {
        super.execute(e);
        Player p = (Player) e.getWhoClicked();
        if (confirmButton.contains(e.getSlot())){
            if (main.getCharacterCreatorManager().hasCreator(p)) {
                main.getCharacterCreatorManager().getCharacterCreator(p).setRace(raceMap.get(current));
                main.getCharacterCreatorManager().getCharacterCreator(p).finishCreator();
            }
        }

    }

    @Override
    public void populateScrollMap() {
        int x= 0;
        for (RaceType type : RaceType.values()){
            x++;
            ItemStack item = new ItemStack(Material.BLAZE_ROD);
            ItemMeta im = item.getItemMeta();
            im.setCustomModelData(type.getDataColor());
            im.setDisplayName(ChatColor.GREEN + type.toString());
            item.setItemMeta(im);
            scrollMap.put(x, item);

        }
    }

    public void populateRaceMap() {
        int x= 0;
        for (RaceType type : RaceType.values()){
            x++;

            raceMap.put(x, type);

        }
    }

    @Override
    public void populateGreyScrollMap() {
        int x= 0;
        for (RaceType type : RaceType.values()){
            x++;
            ItemStack item = new ItemStack(Material.BLAZE_ROD);
            ItemMeta im = item.getItemMeta();
            im.setCustomModelData(type.getDataGrey());
            im.setDisplayName(ChatColor.GRAY + type.toString());
            item.setItemMeta(im);
            greyMap.put(x, item);

        }
    }
}
