package net.uyora.core.menu.menus;

import net.uyora.core.Core;
import net.uyora.core.menu.Menu;
import net.uyora.core.menu.MenuItems;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InteractionMenu extends Menu {

    public Map<EquipmentSlot, Integer> slotMap = new HashMap<EquipmentSlot, Integer>();
    Player player;

    public InteractionMenu(Core main, int size, String name, Player player) {
        super(main,  size, name);

        loadMap();

        MenuItems item = new MenuItems();
        ArrayList<Integer> foreground = new ArrayList<>();
        foreground.add(11); //Head
        foreground.add(19); //Main Hand
        foreground.add(20); //Chest
        foreground.add(21); //Off Hand
        foreground.add(24); //Character Info
        foreground.add(25); //Stats
        foreground.add(29); //Legs
        foreground.add(33); //Trade
        foreground.add(34); //Report
        foreground.add(38); //Boots
        for (int i = 0; i < inventory.getSize(); i++){
            if (!foreground.contains(i)){
                inventory.setItem(i, item.background());
            }
        }
        this.player = player;
        inventory.setItem(24, item.character(player));
        inventory.setItem(25, item.inspect());
        inventory.setItem(33, item.trade());
        inventory.setItem(34, item.report());


        //Player Equipment
        for (EquipmentSlot slot : slotMap.keySet()){
            inventory.setItem(slotMap.get(slot), armor(player,slot) );
        }
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
    public void execute(InventoryClickEvent e){
        super.execute(e);
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 33){
        }

    }

    public void loadMap(){
        slotMap.put(EquipmentSlot.HEAD, 11);
        slotMap.put(EquipmentSlot.CHEST, 20);
        slotMap.put(EquipmentSlot.LEGS, 29 );
        slotMap.put(EquipmentSlot.FEET, 38);
        slotMap.put(EquipmentSlot.OFF_HAND, 21);
        slotMap.put(EquipmentSlot.HAND, 19);
    }

    public ItemStack armor(Player player, EquipmentSlot slot) {
        ItemStack item = player.getInventory().getItem(slot);
        return item;
    }
}
