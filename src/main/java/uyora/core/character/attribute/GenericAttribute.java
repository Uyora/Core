package uyora.core.character.attribute;

import org.bukkit.configuration.ConfigurationSection;
import uyora.core.character.CharacterConfigIO;
import uyora.core.character.CharacterData;
import uyora.core.character.attribute.stat.InnateAttribute;
import uyora.core.character.attribute.stat.PrimaryAttribute;


public class GenericAttribute extends Attribute implements CharacterConfigIO {

	public GenericAttribute(String id) {
		super(id);
	}

	public static GenericAttribute getAttribute(ConfigurationSection section) {
		GenericAttribute attribute;
		switch (section.getName()) {
			case "walk_speed":
				attribute = new InnateAttribute.WalkSpeed();
				break;
			case "hit_chance":
				attribute = new InnateAttribute.HitChance();
				break;
			case "global_cooldown":
				attribute = new InnateAttribute.GlobalCooldown();
				break;
			case "fortitude":
				attribute = new PrimaryAttribute.Fortitude();
				break;
			case "strength":
				attribute = new PrimaryAttribute.Strength();
				break;
			case "dexterity":
				attribute = new PrimaryAttribute.Dexterity();
				break;
			case "intellect":
				attribute = new PrimaryAttribute.Intellect();
				break;
			case "nature_resistance":
				attribute = new PrimaryAttribute.Resistance.Nature();
				break;
			case "fire_resistance":
				attribute = new PrimaryAttribute.Resistance.Fire();
				break;
			case "frost_resistance":
				attribute = new PrimaryAttribute.Resistance.Frost();
				break;
			case "shadow_resistance":
				attribute = new PrimaryAttribute.Resistance.Shadow();
				break;
			default:
				attribute = new GenericAttribute(section.getName());
				break;
		}
		attribute.load(section);
		return attribute;
	}

	@Override
	public void save(ConfigurationSection section) {
		section.set("base", getBase());
	}

	@Override
	public void load(ConfigurationSection section) {
		setBase(section.getDouble("base", 0));
	}

	@Override
	public void modify(CharacterData data) {

	}
}
