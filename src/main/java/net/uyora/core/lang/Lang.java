package net.uyora.core.lang;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
    PREFIX("prefix", "&7&lSYSTEM &8>>"),
    ERROR("error", "&cThere was an issue processing your request. Contact GathrokIronjaw(Josh)");

    private String path;
    private String def;
    private static YamlConfiguration LANG;

    private Lang(String path, String start){
        this.path = path;
        this.def = start;
    }

    public static void setFile(YamlConfiguration configuration) { LANG = configuration;}

    //Translates to color w/ formatting
    public String toString(){
        return this == PREFIX ? ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def)) + " " : ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def));
    }

    //Returns message
    public String getDef(){return this.def;}

    //Returns path
    public String getPath(){return this.path;}
}
