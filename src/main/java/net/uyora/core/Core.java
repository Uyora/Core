package net.uyora.core;

import net.uyora.core.util.StringUtilities;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private StringUtilities stringUtilities = new StringUtilities(this);

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public StringUtilities getStringUtilities() {
        return stringUtilities;
    }

}
