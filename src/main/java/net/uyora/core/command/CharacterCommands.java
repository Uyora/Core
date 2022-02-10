package net.uyora.core.command;

import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CharacterCommands implements CommandExecutor {

    private Core main;

    public CharacterCommands(Core main) {
        this.main = main;
        Bukkit.getPluginCommand("character").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("uyora.admin")) {
                if (command.getName().equalsIgnoreCase("character")) {
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("create")) {
                                main.getCharacterCreatorManager().newCharacterCreator(player);
                                return true;
                            }
                        }
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1] != "") {
                                int character = Integer.parseInt(args[1]);
                                if (main.getUyoraUserManager().getUser(player).hasCharacter(character)) {
                                    main.getUyoraUserManager().getUser(player).setActiveCharacter(character);
                                    player.sendMessage(main.getStringUtilities().TCC("&6&lDeveloper &e>>> &7Character " + character + " has been selected."));
                                } else {
                                    player.sendMessage(main.getStringUtilities().TCC("&6&lDeveloper &e>>> &7That character doesn't exist!"));
                                }
                                return true;
                            }
                        }
                }
            }
        }
        return false;
    }
}
