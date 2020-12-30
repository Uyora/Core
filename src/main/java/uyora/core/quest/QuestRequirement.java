package uyora.core.quest;

import uyora.core.character.CharacterData;

public abstract class QuestRequirement {

	protected abstract boolean isQualified(CharacterData character);

}
