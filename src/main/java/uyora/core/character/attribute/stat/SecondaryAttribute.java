package uyora.core.character.attribute.stat;


import uyora.core.character.attribute.GenericAttribute;

public class SecondaryAttribute extends GenericAttribute {

	public SecondaryAttribute(String id) {
		super(id);
	}

	public static class CriticalChance extends SecondaryAttribute {
		public CriticalChance() {
			super("critical_chance");
		}
	}

	public static class Haste extends SecondaryAttribute {
		public Haste() {
			super("haste");
		}
	}

	public static class Resilience extends SecondaryAttribute {
		public Resilience() {
			super("resilience");
		}
	}

	public static class Focus extends SecondaryAttribute {
		public Focus() {
			super("focus");
		}
	}

	public static class Swiftness extends SecondaryAttribute {
		public Swiftness() {
			super("swiftness");
		}
	}

}
