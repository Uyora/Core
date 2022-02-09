package net.uyora.core.util;

import net.uyora.core.Core;
import org.bukkit.ChatColor;

public class StringUtilities {

    private final Core core;


    public StringUtilities(Core core){
        this.core = core;
    }

    public String TCC(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public String stripCC(String input){
        return ChatColor.stripColor(input);
    }


}
