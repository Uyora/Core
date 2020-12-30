package uyora.core.quest.stage;


import uyora.core.quest.Quest;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.QuestStage;

public class QuestStageDefend extends QuestStage {

	public QuestStageDefend(String id, Quest quest) {
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
