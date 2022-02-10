package net.uyora.core.quest.tracker;

import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.QuestStage;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class QuestTrackerTalk extends QuestProgressionTracker<PlayerInteractEntityEvent> {

	public QuestTrackerTalk(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(PlayerInteractEntityEvent event) {

	}
}
