package net.uyora.core.quest.stage;

import net.uyora.core.quest.Quest;
import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.QuestStage;

public class QuestStageKill extends QuestStage {

	public QuestStageKill(String id, Quest quest) {
		super(id, quest);
	}

	@Override
	public void checkCompletion(QuestProgressionTracker<?> tracker) {

	}

	@Override
	public QuestProgressionTracker<?> setupTracker() {
		return null;
	}
}
