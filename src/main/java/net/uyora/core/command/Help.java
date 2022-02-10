package net.uyora.core.command;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.uyora.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Help implements CommandExecutor {

    private Core main;

    public Help(Core main){
        this.main = main;
        Bukkit.getPluginCommand("uyora").setExecutor(this);
    }

    public void sendHelp(Player player){
        TextComponent createchar = new TextComponent(main.getStringUtilities().TCC("&f&l★ &b/character create"));
        createchar.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.getStringUtilities().TCC("&7manually creates a character"))));
        createchar.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/character create"));

        TextComponent setchar = new TextComponent(main.getStringUtilities().TCC("&f&l★ &b/character set <1-5>"));
        setchar.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.getStringUtilities().TCC("&7manually creates a character"))));
        setchar.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/character set"));

        TextComponent setconfig = new TextComponent(main.getStringUtilities().TCC("&f&l★ &b/config set creatorlocation "));
        setconfig.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.getStringUtilities().TCC("&7sets the character creator's location"))));
        setconfig.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/config set creatorlocation"));

        TextComponent realmConfig = new TextComponent(main.getStringUtilities().TCC("&f&l★ &b/config set realm <realm name> "));
        realmConfig.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.getStringUtilities().TCC("&7sets the current Realm's name"))));
        realmConfig.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/config set realm"));

        TextComponent spawnentity = new TextComponent(main.getStringUtilities().TCC("&f&l★ &b/spawnentity <entity>"));
        spawnentity.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.getStringUtilities().TCC("&7spawns an entity at the Player's location"))));
        spawnentity.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/spawnentity"));


        player.sendMessage(main.getStringUtilities().TCC("&7+------------[&6&lUyora&7]-------------+"));
        player.sendMessage(" ");
        player.spigot().sendMessage(createchar);
        player.sendMessage(" ");
        player.spigot().sendMessage(setchar);
        player.sendMessage(" ");
        player.spigot().sendMessage(setconfig);
        player.sendMessage(" ");
        player.spigot().sendMessage(realmConfig);
        player.sendMessage(" ");
        player.spigot().sendMessage(spawnentity);
        player.sendMessage(" ");
        player.sendMessage(main.getStringUtilities().TCC("&8&o**If you have any questions please contact Josh or Ticxo**"));
        player.sendMessage(" ");
        player.sendMessage(main.getStringUtilities().TCC("&7+---------------------------------+"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("uyora")){
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("Help")){
                        sendHelp(player);
                        return true;
                    }
                }
                if (player.hasPermission("uyora.admin")){
                    sendHelp(player);
                    return true;
                }
            }
        }
        return false;
    }
}
