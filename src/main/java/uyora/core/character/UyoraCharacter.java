package uyora.core.character;

import org.bukkit.entity.Player;
import uyora.core.entity.UyoraEntity;
import uyora.core.menu.charactercreation.NameMenu;
import uyora.core.quest.Quest;
import uyora.core.quest.QuestProgressionTracker;


public class UyoraCharacter extends UyoraEntity {

	private static final int maxChar = 5;

	private final Player player;
	private final CharacterData data;

	private int slot = 0;
	private int charId = 0;

	public UyoraCharacter(Player player) {
		super(player);
		this.player = player;
		data = new CharacterData(this);
		slot = data.countCharacters();
		// For debug
		slot %= maxChar;
	}

	// Character
	public void startCharacterCreation() {
		if(slot >= 5) {
			player.sendMessage("You have reached your maximum character count.");
			return;
		}
		NameMenu menu = new NameMenu(this);
		menu.openMenu(player);
	}

	public void createCharacter(String name, String skinId, CharacterData.Class clazz) {
		if(charId > 0)
			data.saveData(charId);
		// For debug
		slot = slot % maxChar + 1;
		data.createData(slot, name, skinId, clazz);
		charId = slot;
	}

	public int getCharId(){return charId;}

	public void switchCharacter(int id) {
		if(charId > 0)
			data.saveData(charId);
		if(data.loadData(id)) {
			player.sendMessage(String.format("Character set to C%s.", id));
			charId = id;
		}else
			player.sendMessage(String.format("Character C%s does not exists.", id));
	}

	public void saveCharacter() {
		if(charId > 0)
			data.saveData(charId);
	}

	// Quest
	public void acceptQuest(Quest quest) {
		if(data.questTrackers.size() >= 5 || !quest.checkRequirements(data))
			return;
		QuestProgressionTracker<?> tracker = quest.getStage(quest.getStartStage()).setupTracker();
		tracker.setSereniaCharacter(this);
		data.addTracker(quest.getId(), tracker);
	}

	public Player getPlayer() {
		return player;
	}

}
