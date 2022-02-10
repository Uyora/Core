package net.uyora.core.event;

import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import net.uyora.core.menu.menus.InteractionMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Interactions implements Listener {

    private final Core main;

    public Interactions(Core main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity clicked = event.getRightClicked();
        if (clicked instanceof Player) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                if (player.isSneaking()) {
                    Player target = (Player) clicked;
                    if (Bukkit.getOnlinePlayers().contains(target)) {
                        UyoraCharacter character = main.getUyoraUserManager().getUser((Player) clicked).getActiveCharacter();
                        main.getMenuManager().getPlayerSession(player).newMenu(new InteractionMenu(main, 54,
                                main.getStringUtilities().centerText(main.getStringUtilities().TCC("&e&l" + character.getData(CharacterData.NAME)), 39)
                                , target));
                    }
                    if (((Player) clicked).getDisplayName().contains("Gideon")) {
                        //create character
                        main.getCharacterCreatorManager().newCharacterCreator(player);

                    }
                }
            }
        }
    }


}
