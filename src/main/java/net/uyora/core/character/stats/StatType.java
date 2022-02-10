package net.uyora.core.character.stats;

public enum StatType {
    HEALTH("Stats.Health"), DEFENCE("Stats.Defence"), ATTACK("Stats.Attack");

    private String path;

    StatType(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }
}
