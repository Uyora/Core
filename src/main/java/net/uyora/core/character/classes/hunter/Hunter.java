package net.uyora.core.character.classes.hunter;


import net.uyora.core.equipment.ArmorType;
import net.uyora.core.equipment.WeaponType;

import java.util.Arrays;
import java.util.List;

public class Hunter {

    public List<ArmorType> wearableArmorTypes() {
        return Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER);
    }


    public List<WeaponType> usableWeaponTypes() {
        return null;
    }
}
