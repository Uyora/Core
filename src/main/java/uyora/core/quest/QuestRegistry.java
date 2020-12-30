package uyora.core.quest;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class QuestRegistry {

	private static Map<String, Quest> quests = new HashMap<>();

	public static void parseQuest() {
		// Parse yml file to quest object
	}

	public static void registerQuest(Quest quest) {
		quests.put(quest.getId(), quest);
	}

	public static Quest getQuest(String id) {
		return quests.get(id);
	}

	public static QuestProgressionTracker<?> rebuildTrackers(ConfigurationSection section) {
		Quest quest = quests.get(section.getName());
		String stage = section.getString("stage", quest.getStartStage());
		QuestProgressionTracker<?> tracker = quest.getStage(stage).setupTracker();
		tracker.load(section);
		return tracker;
	}

}
