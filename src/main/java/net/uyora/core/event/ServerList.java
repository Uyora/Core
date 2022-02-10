package net.uyora.core.event;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerList implements Listener {

    private Core main;

    public ServerList(Core main) {this.main = main;
       Bukkit.getServer().getPluginManager().registerEvents(this,main);
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMaxPlayers(event.getNumPlayers() + 1);
    }



}
