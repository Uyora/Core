package net.uyora.core.character.classes;

import net.uyora.core.Core;
import org.bukkit.entity.Player;

public abstract class Ability {

    protected final Core main;
    public Ability(Core main){
        this.main = main;
    }

    public abstract void useAbility(Player p);

}
