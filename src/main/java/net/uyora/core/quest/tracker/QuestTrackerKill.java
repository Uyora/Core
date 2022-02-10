package net.uyora.core.quest.tracker;

import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.QuestStage;
import org.bukkit.event.entity.EntityDeathEvent;

public class QuestTrackerKill extends QuestProgressionTracker<EntityDeathEvent> {

	public QuestTrackerKill(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(EntityDeathEvent event) {

	}
}
