package net.uyora.core.quest;

import net.uyora.core.character.CharacterData;

public abstract class QuestRequirement {

	protected abstract boolean isQualified(CharacterData character);

}
