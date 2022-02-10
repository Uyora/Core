package net.uyora.core.character.classes;

import net.uyora.core.Core;
import net.uyora.core.character.classes.skilltrees.SkillTree;
import net.uyora.core.character.classes.skilltrees.Tree;
import net.uyora.core.equipment.ArmorType;
import net.uyora.core.equipment.WeaponType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UyoraClass {

    private final Core main;
    Map<Integer, Tree.TreeName> map = new HashMap<>();

    public UyoraClass(Core main){
        this.main = main;
        map.put(1, skillTreeOne());
        map.put(2, skillTreeTwo());
        map.put(3, skillTreeThree());
    }




    public abstract List<ArmorType> wearableArmorTypes();
    public abstract List<WeaponType> usableWeaponTypes();
    public abstract Tree.TreeName skillTreeOne();
    public abstract Tree.TreeName skillTreeTwo();
    public abstract Tree.TreeName skillTreeThree();

    public void openSkillTree(Player p, int number){
        main.getMenuManager().getPlayerSession(p).newMenu(new SkillTree(main, map.get(number), p));
    }


}
