package net.uyora.core.event;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ResourceGather implements Listener {

    private final Core main;

    public ResourceGather(Core main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onGather(EntityDamageByEntityEvent event) {
        Entity node = event.getEntity();
        if (node.getName().equalsIgnoreCase(main.getStringUtilities().TCC("&8&lIron Ore Node"))) {
            event.setCancelled(true);
            Entity damager = event.getDamager();
            if (damager instanceof Player) {
                Player player = (Player) damager;
                event.setCancelled(true);
                }
            }
        }
    }
