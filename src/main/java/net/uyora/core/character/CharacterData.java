package net.uyora.core.character;

public enum CharacterData {
    LOCATION("Data.Location", 0),
    INVENTORY("Data.Inventory", 0),
    NAME("Name", "none"),
    CLASS("Class", "none"),
    LEVEL("Level" , 0),
    EXPERIENCE("Experience", 0),
    RACE("Race", "none"),
    SKIN_TEXTURE("Data.Skin.Texture", null),
    SKIN_SIGNATURE("Data.Skin.Signature", null),
    GOLD("Balance.Gold", 0),
    SILVER("Balance.Silver", 0),
    COPPER("Balance.Copper", 0),
    GUILD("Guild.Name", "None"),
    QUESTS("Quests", "None"),
    SKILL_POINT("Skills.SkillPoints", 0);

    private String path;
    private Object defaultValue;

    CharacterData(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public String getPath(){
        return path;
    }
    public Object getDefaultValue(){
        return defaultValue;
    }
}
