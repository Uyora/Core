package net.uyora.core.quest.tracker;

import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.QuestStage;
import org.bukkit.event.inventory.InventoryEvent;

public class QuestTrackerGather extends QuestProgressionTracker<InventoryEvent> {

	public QuestTrackerGather(QuestStage stage) {
		super(stage);
	}

	@Override
	public void updateProgress(InventoryEvent event) {

	}
}
