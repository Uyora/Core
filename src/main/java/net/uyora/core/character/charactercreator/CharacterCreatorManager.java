package net.uyora.core.character.charactercreator;

import net.uyora.core.Core;
import net.uyora.core.filemanager.ConfigFileData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CharacterCreatorManager {

    public Map<Player, CharacterCreator> creatorMap = new HashMap<>();

    private final Core main;

    public CharacterCreatorManager(Core main) {
        this.main = main;
    }

    public void newCharacterCreator(Player p) {
        if (main.getConfigFile().getData(ConfigFileData.CREATOR_LOCATION) instanceof Location) {
            if (getNextCharacter(p) != 0) {
                Location loc = (Location) main.getConfigFile().getData(ConfigFileData.CREATOR_LOCATION);
                CharacterCreator creator = new CharacterCreator(p, main);
                creatorMap.put(p, creator);
            } else {
                p.sendMessage(main.getStringUtilities().TCC("&cYou already have the max number of characters allowed!"));
            }
        } else {
            p.sendMessage(ChatColor.RED + "Character Creator Location Has Not Been Set");
        }


    }
    private int getNextCharacter(Player p) {
        for (int x = 1; x <= main.getConfig().getInt("Max_Characters"); x++) {
            File file = new File(main.getDataFolder(), "Players/" + p.getUniqueId() + "/Character" + x +".yml");
            if (!file.exists()) {
                return x;
            }
        }
        return 0;
    }

    public Map<Player, CharacterCreator> getCreatorMap(){
        return creatorMap;
    }

    public boolean hasCreator(Player p){
        if (creatorMap.containsKey(p)){
            return true;
        } else {
            return false;
        }
    }

    public CharacterCreator getCharacterCreator(Player p) {
        return creatorMap.get(p);
    }

    public void removeCharacterCreator(Player p) {
        creatorMap.remove(p);
    }


}
