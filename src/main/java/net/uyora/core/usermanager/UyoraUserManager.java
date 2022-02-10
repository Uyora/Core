package net.uyora.core.usermanager;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class UyoraUserManager {
    private Core main;

    private Map<Player, UyoraUser> playerMap = new HashMap<>();

    public UyoraUserManager(Core main){


        this.main = main;
        for (Player ps : Bukkit.getServer().getOnlinePlayers()){
            loadUser(ps);
        }
    }



    //loads a Serenia user object into the ram
    /*
    List of things this does: loads user object, loads character object, and all files
     */
    public void loadUser(Player p){
        UyoraUser user = new UyoraUser(p, main);
        main.getCharacterManager().loadCharacters(p);
        playerMap.put(p, user);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded user " + p.getName());
    }

    //unloads Serenia user from the ram aswell as the character map
    public void unloadUser(Player p){
        if (playerMap.containsKey(p)) {
            main.getCharacterManager().unloadCharacters(p);
            getUser(p).setActiveCharacter(0);
            playerMap.remove(p);

            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Unloaded user " + p.getName());
        } else {
            System.out.println(main.getStringUtilities().TCC("&CTried to unload but they never loaded for user " + p.getName()));
        }
    }

    //accesses a user that has been loaded into ram
    public UyoraUser getUser(Player p){
        if (playerMap.containsKey(p)) {
            return playerMap.get(p);
        } else {
            Bukkit.getConsoleSender().sendMessage(main.getStringUtilities().TCC("&cTried to getUser " + p.getName() + " But there was an error"));
            return null;
        }
    }
}
