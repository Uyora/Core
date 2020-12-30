package uyora.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import uyora.core.character.CharacterManager;
import uyora.core.command.CharacterCommands;
import uyora.core.listener.MenuListener;
import uyora.core.listener.PlayerListener;
import uyora.core.menu.MenuManager;
import uyora.core.quest.QuestRegistry;
import uyora.core.quest.dev.test;

public final class Main extends JavaPlugin {

    public static Main core;

    private PluginManager pm;
    private CharacterManager characterManager;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        core = this;
        pm = Bukkit.getPluginManager();
        characterManager = new CharacterManager();
        menuManager = new MenuManager();

        pm.registerEvents(new MenuListener(), this);
        pm.registerEvents(new PlayerListener(), this);

        getCommand("character").setExecutor(new CharacterCommands());

        QuestRegistry.registerQuest(new test("kill_pig", "Kill Pigs!", new Vector()));

        for(Player player : Bukkit.getOnlinePlayers()) {
            characterManager.loadCharacter(player);
        }
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            characterManager.unloadCharacter(player);
        }
    }

    public String TCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }
}
