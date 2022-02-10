package net.uyora.core.filemanager;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerFile {
    public Core plugin;
    public FileConfiguration playerdata;
    public File players;

    public PlayerFile(Player p,Core core) {
        this.plugin = core;
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();

        }

        File folder = new File(plugin.getDataFolder(), "Players/" + p.getUniqueId());
        if (!folder.exists()){
            folder.mkdirs();
        }
        this.players = new File(plugin.getDataFolder(), "Players/" + p.getUniqueId() + "/PlayerInfo.yml");
        System.out.println(this.players);
        if (!this.players.exists()) {
            try {
                this.players.createNewFile();

            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.GREEN + "There was an issue creating a player file");
                e.printStackTrace();
            }
        }
        this.playerdata = YamlConfiguration.loadConfiguration(this.players);

    }

    public FileConfiguration getFile() {
        return this.playerdata;
    }

    public void saveFile() {
        try {
            this.playerdata.save(this.players);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't Save Players file. :(");
        }
    }

    public void reloadFile() {
        this.playerdata = YamlConfiguration.loadConfiguration(this.players);
    }
}
