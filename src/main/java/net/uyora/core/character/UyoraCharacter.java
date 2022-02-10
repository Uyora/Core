package net.uyora.core.character;

import net.uyora.core.Core;
import net.uyora.core.character.classes.ClassType;
import net.uyora.core.character.classes.UyoraClass;
import net.uyora.core.character.classes.skilltrees.Tree;
import net.uyora.core.character.races.RaceType;
import net.uyora.core.character.stats.StatType;
import net.uyora.core.filemanager.CharacterFile;
import net.uyora.core.quest.Quest;
import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.QuestRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UyoraCharacter {

    private CharacterFile file;
    private Player p;
    private final Core main;

    private Map<StatType, Integer> statsMap = new HashMap<>();
    private Map<String, QuestProgressionTracker<?>> questMap = new HashMap<>();
    public UyoraCharacter(Core main, Player p, int number) {
        file = new CharacterFile(main,p, number);
        this.p = p;
        this.main = main;
        setUpFile();
        for (StatType stats : StatType.values()) {
            statsMap.put(stats, getConfigStat(stats));
        }
    }

    public void setUpFile() {
        for (CharacterData data : CharacterData.values()) {
            if (getData(data) == null) {
                setData(data, data.getDefaultValue());
            }
        }
        for (StatType stat : StatType.values()) {
            if (file.getFile().get(stat.getPath()) != null) {
                setStat(stat, 0);
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully loaded character file");
    }

    public Object getData(CharacterData data) {
        return file.getFile().get(data.getPath());
    }

    public int getSkillLevel(Tree skill) {
        if (file.getFile().get("Skills." + skill.getTree().toString() + "." + skill.toString()) instanceof Integer) {
            return file.getFile().getInt("Skills." + skill.getTree().toString() + "." + skill.toString());
        } else {
            return 0;
        }
    }

    public void setSkillLevel(Tree skill, int level) {
        file.getFile().set("Skills." + skill.getTree().toString() + "." + skill.toString(), level);
        file.saveFile();
    }

    public void loadStats() {
        for (StatType stat : StatType.values()) {
            statsMap.put(stat, getConfigStat(stat));
        }
    }

    public ClassType getClassType() {
        for (ClassType clas : ClassType.values()) {
            if (clas.toString().equalsIgnoreCase(getData(CharacterData.CLASS).toString())) {
                return clas;
            }
        }
        return null;
    }

    public UyoraClass getActiveClass() {
        return main.getClassManager().getClass(getClassType());
    }

    public RaceType getRaceType() {
        for (RaceType race : RaceType.values()) {
            if (race.toString().equalsIgnoreCase(getData(CharacterData.RACE).toString())) {
                return race;
            }
        }
        return null;
    }


    public int getStat(StatType stat) {
        return statsMap.get(stat);
    }

    public void setStat(StatType stat, int value) {
        statsMap.put(stat, value);
    }

    public int getConfigStat(StatType stat) {
        return file.getFile().getInt(stat.getPath());
    }

    public void setConfigStat(StatType stat, int value) {
        statsMap.put(stat, value);
        file.getFile().set(stat.getPath(), value);
        file.saveFile();
    }

    public void setData(CharacterData data, Object value) {
        file.getFile().set(data.getPath(), value);
        file.saveFile();
    }

    public void setName(String name) {
        file.getFile().set(CharacterData.NAME.getPath(), name);
        file.saveFile();
    }

    public void loadInventory() {
        if (file.getFile().get(CharacterData.INVENTORY.getPath()) instanceof ItemStack[]) {
            ItemStack[] items = (ItemStack[]) file.getFile().get(CharacterData.INVENTORY.getPath());
            int x = 0;
            for (ItemStack item : items) {
                p.getInventory().setItem(x, item);
                x++;
            }
        }
    }

    public void saveInventory() {
        file.getFile().set(CharacterData.INVENTORY.getPath(), p.getInventory().getContents());
        file.saveFile();
    }

    public ItemStack[] getInventoryContents(){
        if (file.getFile().get(CharacterData.INVENTORY.getPath()) instanceof ItemStack[]) {
            ItemStack[] items = (ItemStack[]) file.getFile().get(CharacterData.INVENTORY.getPath());

            return items;
        } else {
            return null;
        }
    }

    public CharacterFile getCharacterFile() {
        return file;
    }

    public void setQuests(String quest){
        if (questMap.size() < 5) {
            Quest q = QuestRegistry.getQuest(quest);
            QuestProgressionTracker<?> tracker = q.getStage(q.getStartStage()).setupTracker();
            // tracker.setSereniaCharacter(this);
            questMap.put(quest, tracker);
            p.sendMessage(main.getStringUtilities().TCC("&6&lQuest Started: &e" + q.getName()));
        }
    }

    public void removeTracker(String quest) {
        questMap.remove(quest);
    }

    public Collection<QuestProgressionTracker<?>> getTracker() {
        return questMap.values();
    }

}
