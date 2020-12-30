package uyora.core.quest.dev;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Pig;
import org.bukkit.event.entity.EntityDeathEvent;
import uyora.core.character.UyoraCharacter;
import uyora.core.quest.QuestStage;
import uyora.core.quest.tracker.QuestTrackerKill;

public class testTracker extends QuestTrackerKill {

    public int pigCounter = 0;
    public UyoraCharacter character;

    public testTracker(QuestStage stage) {
        super(stage);
    }

    @Override
    public void updateProgress(EntityDeathEvent event) {
        if (event.getEntity() instanceof Pig){
            pigCounter += 1;
            System.out.println("Track fired: " + pigCounter);
            stage.checkCompletion(this);
        }
    }

    @Override
    public void save(ConfigurationSection trackerSection) {
        super.save(trackerSection);
        trackerSection.set("kill", pigCounter);
    }

    @Override
    public void load(ConfigurationSection trackerSection) {
        super.load(trackerSection);
        pigCounter = trackerSection.getInt("kill");
    }
}
