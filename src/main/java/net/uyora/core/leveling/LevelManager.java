package net.uyora.core.leveling;

import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import net.uyora.core.filemanager.LevelFile;
import net.uyora.core.particles.LevelUp;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class LevelManager {

    private final Core main;
    private File file;
    private FileConfiguration config;

    public LevelManager(Core main){
        this.main = main;
        LevelFile lvlFile = new LevelFile(main);
        this.file = lvlFile.ExpTable();
        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public int getRequiredExp(int i) {
        return (int) config.get("Level." + i);
    }

    public void levelUp(UyoraCharacter character){
        if (character.getData(CharacterData.LEVEL) instanceof Integer && character.getData(CharacterData.EXPERIENCE) instanceof
        Integer) {
            int level = (int) character.getData(CharacterData.LEVEL);
            int exp = (int) character.getData(CharacterData.EXPERIENCE);
            int newLevel;
            for (ExperienceTable table : ExperienceTable.values()){
                if (exp >= table.getValue()){
                    String data = table.getPath();
                    data = data.replaceAll("Level.", "");
                    newLevel = Integer.parseInt(data);
                    character.setData(CharacterData.LEVEL, newLevel);
                    break;
                }
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (character.getData(CharacterData.NAME) instanceof String) {
                    if (p.getDisplayName().equalsIgnoreCase(main.getStringUtilities().TCC((String) character.getData(CharacterData.NAME)))) {
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                        LevelUp levelUp = new LevelUp(p, main);
                    }
                }
            }




        }
    }

    public void checkExpRequirement(UyoraCharacter character){
        if (character.getData(CharacterData.EXPERIENCE) instanceof Integer && character.getData(CharacterData.LEVEL) instanceof Integer) {
            int xp = (int) character.getData(CharacterData.EXPERIENCE);
            int lvl = getRequiredExp((Integer) character.getData(CharacterData.LEVEL));
            if (xp >= getRequiredExp(lvl)){
                levelUp(character);
            }
        }
    }

    public void setExpBar(Player player, UyoraCharacter character) {
        if (character.getData(CharacterData.EXPERIENCE) instanceof Integer && character.getData(CharacterData.LEVEL) instanceof Integer) {
            int exp = (int) character.getData(CharacterData.EXPERIENCE);
            int level = (int) character.getData(CharacterData.LEVEL);
            float barProgress = exp / getRequiredExp(level);
            player.setExp(barProgress);
        }
    }

}
