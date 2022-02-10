package net.uyora.core.menu;

import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class MenuItems {

    private final Core main = Core.getPlugin(Core.class);

    //General Items

    public ItemStack background(){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    //Report Items
    public ItemStack flight(){
        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Movement Hacks");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack combat(){
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Combat Hacks");
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack esp(){
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ESP Hacks");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack bug(){
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Bug Exploits");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack chat(){
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Chat Abuse");
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    //Interaction Items

    public ItemStack slot(String Slot){
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + Slot);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "Empty");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack inspect(){
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Inspect");
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack trade(){
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Trade");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack report(){
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Report");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack character(Player player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        UyoraCharacter character = main.getUyoraUserManager().getUser(player).getActiveCharacter();
        String name = (String) character.getData(CharacterData.NAME);
        String race = (String) character.getData(CharacterData.RACE);
        String cls = (String) character.getData(CharacterData.CLASS);
        int gold = 0;
        int silver = 0;
        int copper = 0;
        if (character.getData(CharacterData.GOLD) instanceof Integer){
            Integer goldInt = (Integer) character.getData(CharacterData.GOLD);
            gold = goldInt;
        }
        if (character.getData(CharacterData.SILVER) instanceof Integer){
            silver = (int) character.getData(CharacterData.SILVER);
        }
        if (character.getData(CharacterData.COPPER) instanceof Integer){
            copper = (int) character.getData(CharacterData.COPPER);
        }
        String balance = main.getStringUtilities().TCC("&f" + String.valueOf(gold) + "\ueff1 " + String.valueOf(silver) + "\ueff2 " + String.valueOf(copper) +
                "\ueff3");
        int level = 0;
        if (character.getData(CharacterData.LEVEL) instanceof Integer) {
            Integer levelInt = (Integer) character.getData(CharacterData.LEVEL);
            level = levelInt;
        }
        meta.setDisplayName("Party Invite TBD");
        item.setItemMeta(meta);
        return item;
    }





}
