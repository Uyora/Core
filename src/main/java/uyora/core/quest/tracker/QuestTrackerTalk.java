package uyora.core.quest.tracker;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.QuestStage;

public class QuestTrackerTalk extends QuestProgressionTracker<PlayerInteractEntityEvent> {

	public QuestTrackerTalk(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(PlayerInteractEntityEvent event) {

	}
}
