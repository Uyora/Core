package net.uyora.core.quest.requirement;


import net.uyora.core.character.CharacterData;
import net.uyora.core.quest.QuestRequirement;

public class QuestRequirementProfession extends QuestRequirement {

	@Override
	protected boolean isQualified(CharacterData character) {
		return false;
	}
}
