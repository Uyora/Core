package net.uyora.core.character;

import net.uyora.core.Core;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CharacterManager {

    private int maxCharacters = 5;

    private Map<Player, Map<Integer, UyoraCharacter>> charMap = new HashMap<>();

    private final Core main;

    public CharacterManager(Core main) {
        this.main = main;
    }

    public Map<Integer, UyoraCharacter> getCharacterMap(Player p){
        return charMap.get(p);
    }

    //can also be used to create a character
    public void loadCharacter(Player p, int number) {
        Map<Integer, UyoraCharacter> map = charMap.get(p);
        UyoraCharacter character = new UyoraCharacter(main, p, number);
        map.put(number, character);
    }

    //loads all the players character into a map
    public void loadCharacters(Player p) {
        Map<Integer, UyoraCharacter> map = new HashMap<>();
        charMap.put(p, map);
        for (int x = 0; x < maxCharacters; x++) {
            File folder = new File(main.getDataFolder(), "Players/" + p.getUniqueId());

            if (folder.exists()) {
                File file = new File(main.getDataFolder(), "Players/" + p.getUniqueId() + "/Character" + x + ".yml");
                if (file.exists()) {
                    loadCharacter(p, x);
                }
            }


        }

    }
    public void unloadCharacters(Player p){
        saveActiveCharacter(p);
        charMap.remove(p);
    }

    public void saveActiveCharacter(Player p){
        System.out.println(main.getUyoraUserManager().getUser(p));
        if (main.getUyoraUserManager().getUser(p).hasActiveCharacter()) {
            UyoraCharacter character = main.getUyoraUserManager().getUser(p).getActiveCharacter();
            //Stat Saving
            //for (StatType stats : StatType.values()){
             //   character.setConfigStat(stats, character.getStat(stats));
           // }
            //Inventory Saving
            if (!main.getClassManager().isInCombatMenu(p)){
                character.saveInventory();
            }
            //Location Saving
            character.setData(CharacterData.LOCATION, p.getLocation());
        }
    }

}
