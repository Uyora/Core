package net.uyora.core;

import net.uyora.core.character.CharacterManager;
import net.uyora.core.character.charactercreator.CharacterCreatorManager;
import net.uyora.core.character.classes.ClassManager;
import net.uyora.core.command.CharacterCommands;
import net.uyora.core.command.ConfigCommands;
import net.uyora.core.command.Help;
import net.uyora.core.event.*;
import net.uyora.core.filemanager.ConfigFile;
import net.uyora.core.menu.MenuManager;
import net.uyora.core.usermanager.UyoraUserManager;
import net.uyora.core.util.StringUtilities;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private StringUtilities stringUtilities = new StringUtilities(this);

    private CharacterCreatorManager characterCreatorManager;
    private CharacterManager characterManager;
    private UyoraUserManager uyoraUserManager;

    private ClassManager classManager;
    private MenuManager menuManager;

    private ConfigFile configFile;


    @Override
    public void onEnable() {
        // Plugin startup logic
        initClasses();
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initClasses(){
        characterCreatorManager = new CharacterCreatorManager(this);
        characterManager = new CharacterManager(this);
        uyoraUserManager = new UyoraUserManager(this);
        classManager = new ClassManager(this);
        configFile = new ConfigFile(this);
        menuManager = new MenuManager(this);
    }

    public void registerCommands(){
        new CharacterCommands(this);
        new ConfigCommands(this);
        new Help(this);
    }

    public void registerEvents(){
        new Chat(this);
        new Debug(this);
        new DualWielding(this);
        new Interactions(this);
        new JoinLeaveManager(this);
        new QuestListener(this);
        new ResourceGather(this);
        new ServerList(this);
    }


    public StringUtilities getStringUtilities() {
        return stringUtilities;
    }


    public CharacterManager getCharacterManager(){return characterManager;}
    public CharacterCreatorManager getCharacterCreatorManager(){return characterCreatorManager;}
    public UyoraUserManager getUyoraUserManager(){return uyoraUserManager;}
    public ClassManager getClassManager(){return classManager;}
    public MenuManager getMenuManager(){return menuManager;}

    public ConfigFile getConfigFile() {return configFile;}
}
