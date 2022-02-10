package net.uyora.core.event;

import net.uyora.core.Core;
import net.uyora.core.actionbar.TestTracker;
import net.uyora.core.character.UyoraCharacter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Debug implements Listener {

    private Core main;

    public Debug(Core main) {
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    public ItemStack debugItem(){
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.getStringUtilities().TCC("&7>>> &6&lDEBUG Stick &7<<<"));
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().isOp()) {
                    Player player = event.getPlayer();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                        @Override
                        public void run() {
                            player.setGameMode(GameMode.CREATIVE);
                        }
                    },5L);
                    player.sendMessage(main.getStringUtilities().TCC("&3&lWelcome back &b" + player.getDisplayName()));
            if (!player.getInventory().contains(debugItem())) {
                        player.getInventory().addItem(debugItem());
                    }
            }
        }

    @EventHandler
    public void sysTest(PlayerInteractEvent event){
        acceptQuest(event);
    }

    private void acceptQuest(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getHand() != EquipmentSlot.HAND)
            return;
        Player player = e.getPlayer();
        if(!player.getInventory().getItemInMainHand().equals(debugItem()))
            return;
        UyoraCharacter sc = main.getPlugin(Core.class).getUyoraUserManager().getUser(player).getActiveCharacter();
        sc.setQuests("test");
    }

    private void compassTrackerTest(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getHand() != EquipmentSlot.HAND)
            return;
        Player player = e.getPlayer();
        if(!player.getInventory().getItemInMainHand().equals(debugItem()))
            return;
        Location loc = e.getClickedBlock().getLocation().add(0.5, 1, 0.5);
        TestTracker tracker = new TestTracker(loc);
        player.sendMessage(String.format("Location tracked: %s, %s, %s.", loc.getX(), loc.getY(), loc.getZ()));
    }

}
