package net.uyora.core.quest;


import net.uyora.core.character.UyoraCharacter;

public abstract class QuestReward {

	protected abstract void reward(UyoraCharacter character);

	protected abstract String format();

}
