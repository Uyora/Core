package net.uyora.core.command;

import net.uyora.core.Core;
import net.uyora.core.filemanager.ConfigFileData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommands implements CommandExecutor {

    private Core main;

    public ConfigCommands(Core main){
        this.main = main;
        Bukkit.getPluginCommand("config").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("config")) {
                if (sender.hasPermission("uyora.admin")) {
                    if (args.length > 0) {
                        if (args[0] != null) {
                            if (args[0].equalsIgnoreCase("set")) {
                                if (args[1] != "") {
                                    if (args[1].equalsIgnoreCase("creatorlocation")) {
                                        main.getConfigFile().setData(ConfigFileData.CREATOR_LOCATION, player.getLocation());
                                        player.sendMessage(main.getStringUtilities().TCC("&3&lAdmin &b>>> &7Character creator location saved!"));
                                        return true;
                                    }
                                    if (args[1].equalsIgnoreCase("realm")) {
                                        if (args[2] != "") {
                                            main.getConfigFile().setData(ConfigFileData.REALM_NAME, args[2]);
                                            player.sendMessage(main.getStringUtilities().TCC("&3&lAdmin &b>>> &7Realm name set to: &f&l" + args[2] + "&7."));
                                        } else {
                                            player.sendMessage(main.getStringUtilities().TCC("&3&lAdmin &b>>> &7Realm name invalid!"));
                                        }
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
