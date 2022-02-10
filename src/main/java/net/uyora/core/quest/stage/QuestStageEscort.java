package net.uyora.core.quest.stage;


import net.uyora.core.quest.Quest;
import net.uyora.core.quest.QuestProgressionTracker;
import net.uyora.core.quest.QuestStage;

public class QuestStageEscort extends QuestStage {

	public QuestStageEscort(String id, Quest quest) {
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
