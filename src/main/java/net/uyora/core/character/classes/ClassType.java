package net.uyora.core.character.classes;


import net.uyora.core.character.classes.warrior.Warrior;

public enum ClassType {

    WARRIOR(7, 17, "\uF015", Warrior.class),
    MAGE(5, 15, "\uF014", Warrior.class),
    ELEMENTALIST(3, 13, "\uF012", Warrior.class),
    HUNTER(4, 14, "\uF013", Warrior.class),
    ASSASSIN(1, 11, "\uF010", Warrior.class),
    CLERIC(2, 12, "\uF011", Warrior.class),
    PALADIN(6, 16, "\uF016", Warrior.class);

    private int modelData;
    private int greyData;
    private String icon;
    private Class<? extends UyoraClass> clazz;

    ClassType(int modelData, int greyData, String icon, Class<? extends UyoraClass> clazz) {
        this.modelData = modelData;
        this.greyData = greyData;
        this.icon = icon;
        this.clazz = clazz;
    }

    public int getModelData() {
        return modelData;
    }

    public String getIcon(){
        return icon;
    }

    public int getGreyData() {
        return greyData;
    }

    public Class<? extends UyoraClass> getClazz() {
        return clazz;
    }
}
