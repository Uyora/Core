package net.uyora.core.character.name;

import net.uyora.core.Core;
import net.uyora.core.character.UyoraCharacter;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


public class PlayerNameHandler {

    private Player player;
    private UyoraCharacter character;
    private final Core main = Core.getPlugin(Core.class);

    public PlayerNameHandler(Player player){
        this.player = player;
        character = main.getUyoraUserManager().getUser(player).getActiveCharacter();
    }


    public void setName() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        int health = (int) player.getHealth();
        int maxHealth = (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        Objective healthobjective = board.registerNewObjective("health", "health", main.getStringUtilities().TCC( "&7/" + "&c" + maxHealth));
        healthobjective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        healthobjective.getScore("health").setScore(health);


        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setScoreboard(board);
            online.setHealth(online.getHealth()); //Update their health
        }
    }
}
