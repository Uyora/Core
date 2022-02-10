package net.uyora.core.character.classes.warrior;

import net.uyora.core.Core;
import net.uyora.core.character.classes.UyoraClass;
import net.uyora.core.character.classes.skilltrees.Tree;
import net.uyora.core.equipment.ArmorType;
import net.uyora.core.equipment.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends UyoraClass {


    private List<ArmorType> armorTypes = new ArrayList<>();
    private List<WeaponType> weaponTypes = new ArrayList<>();

    public Warrior(Core main) {
        super(main);
        armorTypes.add(ArmorType.PLATE);
        weaponTypes.add(WeaponType.SWORD);
    }

    @Override
    public List<ArmorType> wearableArmorTypes() {
        return armorTypes;
    }

    @Override
    public List<WeaponType> usableWeaponTypes() {
        return weaponTypes;
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return Tree.TreeName.BLOODLUST;
    }

    @Override
    public Tree.TreeName skillTreeTwo() {
        return Tree.TreeName.FORCE;
    }

    @Override
    public Tree.TreeName skillTreeThree() {
        return Tree.TreeName.FORTITUDE;
    }


}
