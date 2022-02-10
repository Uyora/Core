package net.uyora.core.quest.requirement;


import net.uyora.core.character.CharacterData;
import net.uyora.core.quest.QuestRequirement;

public class QuestRequirementQuest extends QuestRequirement {

	@Override
	protected boolean isQualified(CharacterData character) {
		return false;
	}
}
