package net.uyora.core.event;

import net.uyora.core.Core;
import net.uyora.core.chat.ChatHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    private Core main;

    public Chat(Core main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ChatHandler chat = new ChatHandler(player, event.getMessage(), Bukkit.getOnlinePlayers());
        event.setCancelled(true);
    }

}
