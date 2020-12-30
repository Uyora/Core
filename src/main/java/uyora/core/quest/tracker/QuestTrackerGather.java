package uyora.core.quest.tracker;

import org.bukkit.event.inventory.InventoryEvent;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.QuestStage;


public class QuestTrackerGather extends QuestProgressionTracker<InventoryEvent> {

	public QuestTrackerGather(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(InventoryEvent event) {

	}
}
