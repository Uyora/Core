package uyora.core.character.attribute;


import uyora.core.character.CharacterData;
import uyora.core.character.CharacterDataModifier;

public abstract class Attribute implements CharacterDataModifier {

	protected final String attributeId;

	protected double base = 0;

	public Attribute(String id) {
		attributeId = id;
	}

	public void setBase(double point) {
		base = point;
	}

	public void reference(CharacterData data) {

	}

	public double getBase() {
		return base;
	}

	public double getFinal(CharacterData data) {
		return getBase();
	}

	public String getId() {
		return attributeId;
	}

}
