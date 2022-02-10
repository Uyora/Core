package net.uyora.core.event;

import net.uyora.core.Core;
import net.uyora.core.character.UyoraCharacter;
import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.tracker.QuestTrackerKill;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class QuestListener implements Listener {

    private Core main;

    public QuestListener(Core main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        UyoraCharacter sc = main.getUyoraUserManager().getUser(event.getEntity().getKiller()).getActiveCharacter();
        for(QuestProgressionTracker<?> tracker : sc.getTracker()) {
            if(tracker instanceof QuestTrackerKill) {
                ((QuestTrackerKill) tracker).updateProgress(event);
            }
        }
    }

}
