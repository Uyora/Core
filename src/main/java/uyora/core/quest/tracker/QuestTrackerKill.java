package uyora.core.quest.tracker;

import org.bukkit.event.entity.EntityDeathEvent;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.QuestStage;

public class QuestTrackerKill extends QuestProgressionTracker<EntityDeathEvent> {

	public QuestTrackerKill(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(EntityDeathEvent event) {

	}
}
