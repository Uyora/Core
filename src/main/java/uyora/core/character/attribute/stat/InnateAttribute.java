package uyora.core.character.attribute.stat;


import uyora.core.character.attribute.GenericAttribute;

public class InnateAttribute extends GenericAttribute {

	public InnateAttribute(String id) {
		super(id);
	}

	public static class WalkSpeed extends InnateAttribute {
		public WalkSpeed() {
			super("walk_speed");
		}
	}

	public static class HitChance extends InnateAttribute {
		public HitChance() {
			super("hit_chance");
		}
	}

	public static class GlobalCooldown extends InnateAttribute {
		public GlobalCooldown() {
			super("global_cooldown");
		}
	}

}
