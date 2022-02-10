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

    public String centerText(String text, int lineLength) {
        StringBuilder builder = new StringBuilder(text);
        char space = ' ';
        int distance = (lineLength - text.length()) / 2;
        for (int i = 0; i < distance; ++i) {
            builder.insert(0, space);
            builder.append(space);
        }
        return builder.toString();

    }


}
