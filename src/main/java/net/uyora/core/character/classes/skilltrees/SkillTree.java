package net.uyora.core.character.classes.skilltrees;

import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import net.uyora.core.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkillTree extends Menu {

    private Player p;
    private List<Tree> skills = new ArrayList<>();
    private final Core main;


    public SkillTree(Core main, Tree.TreeName name, Player p) {
        super(main, 54, name.toString());
        this.main = main;
        this.p = p;
        for (Tree t : Tree.values()) {
            if (t.getTree().equals(name)) {

                inventory.setItem(t.getSlot(), skillItem(t));
                skills.add(t);
            }
        }
    }

    public ItemStack skillItem(Tree t){
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(main.getStringUtilities().TCC("&a" + t.toString().replaceAll("_", " ")));
        im.setCustomModelData(t.getCustomItemData());
        item.setItemMeta(im);
        return item;
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }

    @Override
    public void execute(InventoryClickEvent e) {
        if (e.getCurrentItem() == null)
            return;
        if (e.getCurrentItem().getItemMeta() == null)
            return;
        Tree tree = null;
        int totalPoints = 0;
        UyoraCharacter character = main.getUyoraUserManager().getUser(p).getActiveCharacter();
        for (Tree t : skills) {
            if (e.getSlot() == t.getSlot()) {
                tree = t;
                totalPoints = totalPoints + character.getSkillLevel(t);
                break;
            }
        }
        if (tree == null)
            return;
        int tier = tree.getTier();
        int requiredPoints = tier * 5 - 5;
        if (totalPoints < requiredPoints) {
            p.sendMessage(main.getStringUtilities().TCC("&cThis skill requires " + requiredPoints + " put into this skill tree!"));
            return;
        } else {
            try {
                if ((int) character.getData(CharacterData.SKILL_POINT) > 0) {
                    character.setData(CharacterData.SKILL_POINT, (int) character.getData(CharacterData.SKILL_POINT) - 1);
                    character.setSkillLevel(tree, character.getSkillLevel(tree) + 1);
                    e.getClickedInventory().setItem(e.getSlot(), skillItem(tree));
                }
            } catch (ClassCastException n){

                System.out.println(main.getStringUtilities().TCC("&c&lTried to fetch the users skill points but it returned a value that was not an integer"));

                n.printStackTrace();
            }
        }
    }
}
