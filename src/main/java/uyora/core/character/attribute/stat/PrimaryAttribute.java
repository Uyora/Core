package uyora.core.character.attribute.stat;


import uyora.core.character.attribute.GenericAttribute;

public class PrimaryAttribute extends GenericAttribute {

	public PrimaryAttribute(String id) {
		super(id);
	}

	public static class Fortitude extends PrimaryAttribute {
		public Fortitude() {
			super("fortitude");
		}
	}

	public static class Strength extends PrimaryAttribute {
		public Strength() {
			super("strength");
		}
	}

	public static class Dexterity extends PrimaryAttribute {
		public Dexterity() {
			super("dexterity");
		}
	}

	public static class Intellect extends PrimaryAttribute {
		public Intellect() {
			super("intellect");
		}
	}

	public static class Resistance extends PrimaryAttribute {
		public Resistance(String id) {
			super(id + "_resistance");
		}

		public static class Nature extends Resistance {
			public Nature() {
				super("nature");
			}
		}

		public static class Fire extends Resistance {
			public Fire() {
				super("fire");
			}
		}

		public static class Frost extends Resistance {
			public Frost() {
				super("frost");
			}
		}

		public static class Shadow extends Resistance {
			public Shadow() {
				super("shadow");
			}
		}

	}

}
