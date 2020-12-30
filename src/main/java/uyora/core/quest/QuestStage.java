package uyora.core.quest;

public abstract class QuestStage {

	protected String id;
	protected Quest quest;

	public QuestStage(String id, Quest quest) {
		this.id = id;
		this.quest = quest;
		quest.registerStage(id, this);
	}

	public abstract void checkCompletion(QuestProgressionTracker<?> tracker);

	public abstract QuestProgressionTracker<?> setupTracker();

}
