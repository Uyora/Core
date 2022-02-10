package net.uyora.core.chat;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import org.bukkit.entity.Player;

import java.util.Collection;


public class ChatHandler {
    /*
    Global
    Trade
    LFG
    Staff

     */
    

    private Player player;
    private final Core main = Core.getPlugin(Core.class);
    UyoraCharacter character;

    public ChatHandler(Player player, String message, Collection<? extends Player> recipients){
        character = main.getUyoraUserManager().getUser(player).getActiveCharacter();
        String name = (String) character.getData(CharacterData.NAME);
        String race = (String) character.getData(CharacterData.RACE);
        String clas = (String) character.getData(CharacterData.CLASS);
        String guild = (String) character.getData(CharacterData.GUILD);
        if (guild == null) {
            guild = main.getStringUtilities().TCC("&aNot in a guild");
        }
        int level = (int) character.getData(CharacterData.LEVEL);
        TextComponent namecomp = new TextComponent(main.getStringUtilities().TCC("&6(&e" + name + "&6)"));
        namecomp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                main.getStringUtilities().TCC("&aLevel &2&l" + level + " &7" + race + " " + clas + "\n"+ "&b♜ " + guild)
        ).create()));
        TextComponent messagecomp = new TextComponent(main.getStringUtilities().TCC("&r " + message));
        for (Player p : recipients){
            p.spigot().sendMessage(namecomp,messagecomp);
        }
    }

}
