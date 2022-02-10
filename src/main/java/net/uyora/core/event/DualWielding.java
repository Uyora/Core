package net.uyora.core.event;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class DualWielding implements Listener {

    private final Core main;

    public DualWielding(Core main) {
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                p.swingOffHand();



            }
        }
    }

    @EventHandler
    public void onClickEntity(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)) {

            p.swingOffHand();
            if (e.getRightClicked() instanceof LivingEntity) {
                ((LivingEntity) e.getRightClicked()).damage(10, p);
            }
        }

    }


}
