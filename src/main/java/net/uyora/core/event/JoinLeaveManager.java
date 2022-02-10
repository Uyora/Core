package net.uyora.core.event;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class JoinLeaveManager implements Listener {

    private final Core main;

    public JoinLeaveManager(Core main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }
    

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        main.getUyoraUserManager().loadUser(p);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        main.getUyoraUserManager().unloadUser(p);
    }



}
