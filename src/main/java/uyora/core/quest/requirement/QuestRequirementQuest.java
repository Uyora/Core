package uyora.core.quest.requirement;


import uyora.core.character.CharacterData;
import uyora.core.quest.QuestRequirement;

public class QuestRequirementQuest extends QuestRequirement {

	@Override
	protected boolean isQualified(CharacterData character) {
		return false;
	}
}
