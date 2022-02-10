package net.uyora.core.character.classes;

import net.uyora.core.Core;
import net.uyora.core.character.UyoraCharacter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassManager implements Listener {

    private Map<ClassType, UyoraClass> classMap = new HashMap<>();
    private List<Player> combatMenu = new ArrayList<>();

    private final Core main;


    public ClassManager(Core main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
        loadClasses();
    }

    public UyoraClass getClass(ClassType type){
        return classMap.get(type);
    }

    public void loadClasses(){
        for (ClassType type : ClassType.values()){
            try {
                classMap.put(type, type.getClazz().getConstructor(Core.class).newInstance(main));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e){
        Player p = e.getPlayer();
        if (main.getUyoraUserManager().getUser(p).hasActiveCharacter()) {
            e.setCancelled(true);
            toggleCombatInventory(p);
        }
    }

    public void toggleCombatInventory(Player p){
        if (combatMenu.contains(p)){
            combatMenu.remove(p);
            main.getUyoraUserManager().getUser(p).getActiveCharacter().loadInventory();
        } else {
            combatMenu.add(p);
            UyoraCharacter character = main.getUyoraUserManager().getUser(p).getActiveCharacter();
            character.saveInventory();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    ItemStack[] clear = {new ItemStack(Material.AIR), new ItemStack(Material.AIR)};

                    p.getInventory().setContents(clear);

                    p.getInventory().setHelmet(character.getInventoryContents()[character.getInventoryContents().length - 2]);
                    p.getInventory().setChestplate(character.getInventoryContents()[character.getInventoryContents().length - 3]);
                    p.getInventory().setLeggings(character.getInventoryContents()[character.getInventoryContents().length - 4]);
                    p.getInventory().setBoots(character.getInventoryContents()[character.getInventoryContents().length - 5]);
                }
            }, 1L);
        }
    }

    public boolean isInCombatMenu(Player p){
        if (combatMenu.contains(p)){
            return true;
        }
        return false;
    }



}
