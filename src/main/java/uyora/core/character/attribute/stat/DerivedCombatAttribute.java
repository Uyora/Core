package uyora.core.character.attribute.stat;


import uyora.core.character.CharacterData;
import uyora.core.character.attribute.DerivedAttribute;

public class DerivedCombatAttribute extends DerivedAttribute {

	public DerivedCombatAttribute(String id) {
		super(id);
	}

	public static class Physical extends DerivedCombatAttribute {
		public Physical(String id) {
			super(id);
		}

		public static class MainWeaponDamage extends Physical {

			protected MeleeAttackPower melee;
			protected RangeAttackPower range;

			public MainWeaponDamage() {
				super("main_weapon_damage");
			}

			public MainWeaponDamage(String id) {
				super(id);
			}

			@Override
			public void reference(CharacterData data) {
				melee = (MeleeAttackPower) data.getAttribute("melee_attack_power");
				range = (RangeAttackPower) data.getAttribute("range_attack_power");
			}

			@Override
			public double getFinal(CharacterData data) {
				return super.getFinal(data);
			}
		}

		public static class SubWeaponDamage extends MainWeaponDamage {
			public SubWeaponDamage() {
				super("sub_weapon_damage");
			}

			@Override
			public double getFinal(CharacterData data) {
				return super.getFinal(data) / 2;
			}
		}

		public static class MeleeAttackPower extends Physical {

			private PrimaryAttribute.Strength strength;
			private PrimaryAttribute.Dexterity dexterity;

			public MeleeAttackPower() {
				super("melee_attack_power");
			}

			@Override
			public void reference(CharacterData data) {
				strength = (PrimaryAttribute.Strength) data.getAttribute("strength");
				dexterity = (PrimaryAttribute.Dexterity) data.getAttribute("dexterity");
			}
		}

		public static class RangeAttackPower extends Physical {

			private PrimaryAttribute.Dexterity dexterity;

			public RangeAttackPower() {
				super("range_attack_power");
			}

			@Override
			public void reference(CharacterData data) {
				dexterity = (PrimaryAttribute.Dexterity) data.getAttribute("dexterity");
			}

		}

		public static class MeleeAttackSpeed extends Physical {
			public MeleeAttackSpeed() {
				super("melee_attack_speed");
			}
		}

		public static class RangeAttackSpeed extends Physical {
			public RangeAttackSpeed() {
				super("range_attack_speed");
			}
		}

		public static class CriticalStrike extends Physical {
			public CriticalStrike() {
				super("physical_critical_strike");
			}
		}

	}

	public static class Spell extends DerivedCombatAttribute {
		public Spell(String id) {
			super(id);
		}

		public static class CastingSpeed extends Spell {
			public CastingSpeed() {
				super("casting_speed");
			}
		}

		public static class CriticalStrike extends Spell {
			public CriticalStrike() {
				super("spell_critical_strike");
			}
		}

		public static class Regeneration extends Spell {
			public Regeneration(String id) {
				super(id + "_regeneration");
			}

			public static class Mana extends Regeneration {
				public Mana() {
					super("mana");
				}
			}

			public static class Rage extends Regeneration {
				public Rage() {
					super("rage");
				}
			}

			public static class Element extends Regeneration {
				public Element() {
					super("element");
				}
			}

			public static class Energy extends Regeneration {
				public Energy() {
					super("energy");
				}
			}

		}



	}

	public static class Defense extends DerivedCombatAttribute {
		public Defense(String id) {
			super(id);
		}

		public static class Armor extends Defense {
			public Armor() {
				super("armor");
			}
		}

		public static class Dodge extends Defense {
			public Dodge() {
				super("dodge");
			}
		}

		public static class Parry extends Defense {
			public Parry() {
				super("parry");
			}
		}

		public static class BlockRating extends Defense {
			public BlockRating() {
				super("block_rating");
			}
		}

		public static class Adaptation extends Defense {
			public Adaptation() {
				super("adaptation");
			}
		}

	}

}
