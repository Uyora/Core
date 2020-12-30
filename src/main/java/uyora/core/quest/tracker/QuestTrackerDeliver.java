package uyora.core.quest.tracker;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.QuestStage;

public class QuestTrackerDeliver extends QuestProgressionTracker<PlayerInteractEntityEvent> {

	public QuestTrackerDeliver(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(PlayerInteractEntityEvent event) {

	}
}
