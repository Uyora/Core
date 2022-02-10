package net.uyora.core.character.charactercreator;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import net.uyora.core.character.classes.ClassType;
import net.uyora.core.character.races.RaceType;
import net.uyora.core.filemanager.ConfigFileData;
import net.uyora.core.menu.menus.SelectClass;
import net.uyora.core.menu.menus.SelectRace;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.codehaus.plexus.util.Base64;

import java.io.File;
import java.lang.reflect.Field;
import java.util.UUID;

public class CharacterCreator {


    private int character;


    private final Core main;
    private Location loc;
    private final Player player;

    public CharacterCreator(Player p, Core main) {
        this.main = main;
        this.player = p;
        loc = (Location) main.getConfigFile().getData(ConfigFileData.CREATOR_LOCATION);
        startCreator();
    }

    public Boolean hasMaxCharacters(){
        if (getNextCharacter() >= main.getConfig().getInt("Max_Characters")){
            return true;
        }
        return false;
    }

    private int getNextCharacter() {
        for (int x = 1; x <= main.getConfig().getInt("Max_Characters"); x++) {
            File file = new File(main.getDataFolder(), "Players/" + player.getUniqueId() + "/Character" + x +".yml");
            if (!file.exists()) {
                return x;
            }
        }
        return 0;
    }

    public void startCreator() {
        //TODO: teleport player to location
        //TODO: spawn NPC and buttons
        character = getNextCharacter();
        main.getCharacterManager().loadCharacter(player, character);
        setName();
    }


    public void finishCreator() {
        //TODO: remove npc and buttons
        //TODO: set active character and load into world
        main.getUyoraUserManager().getUser(player).setActiveCharacter(character);
        main.getCharacterCreatorManager().removeCharacterCreator(player);
        player.closeInventory();
    }

    public ItemStack getSkull(String url) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(url.isEmpty())return head;


        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public void openClassSelector() {
        main.getMenuManager().getPlayerSession(player).newMenu(new SelectClass(main, "Select a class"));
    }

    public void openRaceSelector() {
        main.getMenuManager().getPlayerSession(player).newMenu(new SelectRace(main, "Select a Race"));
    }

    public void openSkinSelector() {

    }

    public void setClass(ClassType classType) {
        UyoraCharacter chars = main.getCharacterManager().getCharacterMap(player).get(character);
        chars.setData(CharacterData.CLASS, classType.toString());
    }

    public void setRace(RaceType raceType) {
        UyoraCharacter chars = main.getCharacterManager().getCharacterMap(player).get(character);
        chars.setData(CharacterData.RACE, raceType.toString());
    }

    public void setSkin() {
        //TODO Mineskin hook
    }

    public void setName() {
        String name = "";
        (new AnvilGUI.Builder()).onClose((p) -> {
        }).onComplete((p, text) -> {
            p.sendMessage(String.valueOf(character));
            if (text.length() <= 12 && !Bukkit.getOnlinePlayers().contains(text)) {
                UyoraCharacter chars = main.getCharacterManager().getCharacterMap(p).get(character);
                chars.setName(text);
                openClassSelector();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                return AnvilGUI.Response.close();
            } else {
                return AnvilGUI.Response.text("Invalid Name! Name is in use or exceeds 12 characters.");
            }
        })
                .text("Select a name")
                .item(new ItemStack(Material.NAME_TAG))
                .title(ChatColor.DARK_GREEN + "What's your name?")
                .plugin(main)
                .open(player);

    }

}
