package net.uyora.core.quest;

import net.uyora.core.character.CharacterConfigIO;
import net.uyora.core.character.UyoraCharacter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

/**
 * Stored in player and keep track of their stats.
 * @param <T>
 */
public abstract class QuestProgressionTracker<T extends Event> implements CharacterConfigIO {

	protected QuestStage stage;
	protected UyoraCharacter character;

	// TODO Create tracker for other stages
	public QuestProgressionTracker(QuestStage stage) {
		this.stage = stage;
	}

	public abstract void updateProgress(T event);

	public void setSereniaCharacter(UyoraCharacter character) {
		this.character = character;
	}

	public UyoraCharacter getSereniaCharacter() {
		return character;
	}

	@Override
	public void save(ConfigurationSection trackerSection) {
		trackerSection.set("stage", stage.id);
	}

	@Override
	public void load(ConfigurationSection trackerSection) {

	}
}
