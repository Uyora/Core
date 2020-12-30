package uyora.core.character;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;
import uyora.core.Main;
import uyora.core.character.attribute.Attribute;
import uyora.core.character.attribute.GenericAttribute;
import uyora.core.character.attribute.stat.DerivedCombatAttribute;
import uyora.core.character.attribute.stat.InnateAttribute;
import uyora.core.character.attribute.stat.PrimaryAttribute;
import uyora.core.character.attribute.stat.SecondaryAttribute;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.QuestRegistry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CharacterData {

	protected final File playerFolder;
	protected final UyoraCharacter uyoraCharacter;
	protected final Map<String, Attribute> attributes = new HashMap<>();
	protected final Map<String, QuestProgressionTracker<?>> questTrackers = new HashMap<>();

	protected String name;
	protected String skinId;
	protected Class clazz;
	protected int level = 1;
	protected int experience = 0;
	protected String guild;
	protected int balance = 0;
	protected int health = 0;
	protected int maxHealth = 0;
	protected int mana = 0;
	protected int maxMana = 0;

	public CharacterData(UyoraCharacter uyoraCharacter) {
		this.uyoraCharacter = uyoraCharacter;
		this.playerFolder = new File(Main.getPlugin(Main.class).getDataFolder(), "players/" + uyoraCharacter.getPlayer().getUniqueId());
		if(!playerFolder.exists())
			playerFolder.mkdirs();
	}

	// Data IO
	public void createData(int id, String name, String skinId, Class clazz) {
		resetCharacter();
		this.name = name;
		this.skinId = skinId;
		this.clazz = clazz;
		initializeAttributes();

		saveData(id);
	}

	public void saveData(int id) {
		File character = getCharacterFile(id);
		YamlConfiguration data = new YamlConfiguration();
		data.set("location", uyoraCharacter.getPlayer().getLocation().toVector());
		data.set("name", name);
		data.set("skin_id", skinId);
		data.set("class", clazz.toString());
		data.set("level", level);
		data.set("experience", experience);
		data.set("guild", guild);
		data.set("balance", balance);

		ConfigurationSection quests = data.createSection("quests");
		for(String questId : questTrackers.keySet()) {
			QuestProgressionTracker<?> tracker = questTrackers.get(questId);
			ConfigurationSection quest = quests.createSection(questId);
			tracker.save(quest);
		}

		ConfigurationSection stats = data.createSection("stats");
		for(Attribute attribute : attributes.values()) {
			if(attribute instanceof GenericAttribute) {
				ConfigurationSection attr = stats.createSection(attribute.getId());
				((GenericAttribute) attribute).save(attr);
			}
		}

		try {
			data.save(character);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param id - Character ID
	 * @return false when the character file does not exist
	 */
	public boolean loadData(int id) {
		File character = new File(playerFolder, id + ".yml");
		if(!character.exists())
			return false;
		YamlConfiguration data = YamlConfiguration.loadConfiguration(character);

		Vector vec = data.getVector("location");
		if(vec != null) {
			Location location = uyoraCharacter.getPlayer().getLocation();
			location.setX(vec.getX());
			location.setY(vec.getY());
			location.setZ(vec.getZ());
			uyoraCharacter.getPlayer().teleport(location);
		}

		name = data.getString("name");
		skinId = data.getString("skin_id");
		clazz = Class.get(data.getString("class"));
		level = data.getInt("level", 1);
		experience = data.getInt("experience", 0);
		guild = data.getString("guild", "");
		balance = data.getInt("balance", 0);

		ConfigurationSection quests = data.getConfigurationSection("quests");
		if(quests != null) {
			for (String path : quests.getKeys(false)) {
				ConfigurationSection attr = quests.getConfigurationSection(path);
				questTrackers.put(attr.getName(), QuestRegistry.rebuildTrackers(attr));
			}
		}

		ConfigurationSection stats = data.getConfigurationSection("stats");
		if(stats != null) {
			for (String path : stats.getKeys(false)) {
				ConfigurationSection attr = stats.getConfigurationSection(path);
				attributes.put(attr.getName(), GenericAttribute.getAttribute(attr));
			}
		}
		initializeAttributes();
		for(Attribute attribute : attributes.values())
			attribute.reference(this);

		return true;
	}

	public File getCharacterFile(int id) {
		File character = new File(playerFolder, id + ".yml");
		if(!character.exists()) {
			try {
				character.createNewFile();
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(String.format("An error occurred while creating a new character file for %s: ID %s", uyoraCharacter.getPlayer().getName(), id));
				e.printStackTrace();
			}
		}
		return character;
	}

	public int countCharacters() {
		for(int i = 1; i <= 5; ++i) {
			if(!new File(playerFolder, i + ".yml").exists())
				return i - 1;
		}
		return 5;
	}

	private void resetCharacter() {
		attributes.clear();
		questTrackers.clear();

		name = "";
		skinId = "";
		clazz = Class.NONE;
		level = 1;
		experience = 0;
		guild = "";
		balance = 0;
		health = 0;
		maxHealth = 0;
		mana = 0;
		maxMana = 0;
	}

	// Attributes
	public Attribute getAttribute(String id) {
		return attributes.get(id);
	}

	private void initializeAttributes() {
		// Generic
		// Innate
		putAttribute(new InnateAttribute.WalkSpeed(), false);
		putAttribute(new InnateAttribute.HitChance(), false);
		putAttribute(new InnateAttribute.GlobalCooldown(), false);
		// Primary
		putAttribute(new PrimaryAttribute.Fortitude(), false);
		putAttribute(new PrimaryAttribute.Strength(), false);
		putAttribute(new PrimaryAttribute.Dexterity(), false);
		putAttribute(new PrimaryAttribute.Intellect(), false);
		putAttribute(new PrimaryAttribute.Resistance.Nature(), false);
		putAttribute(new PrimaryAttribute.Resistance.Fire(), false);
		putAttribute(new PrimaryAttribute.Resistance.Frost(), false);
		putAttribute(new PrimaryAttribute.Resistance.Shadow(), false);
		// Secondary
		putAttribute(new SecondaryAttribute.CriticalChance(), false);
		putAttribute(new SecondaryAttribute.Haste(), false);
		putAttribute(new SecondaryAttribute.Resilience(), false);
		putAttribute(new SecondaryAttribute.Focus(), false);
		putAttribute(new SecondaryAttribute.Swiftness(), false);

		// Derived
		// Physical
		putAttribute(new DerivedCombatAttribute.Physical.MainWeaponDamage(), true);
		putAttribute(new DerivedCombatAttribute.Physical.SubWeaponDamage(), true);
		putAttribute(new DerivedCombatAttribute.Physical.MeleeAttackPower(), true);
		putAttribute(new DerivedCombatAttribute.Physical.RangeAttackPower(), true);
		putAttribute(new DerivedCombatAttribute.Physical.MeleeAttackSpeed(), true);
		putAttribute(new DerivedCombatAttribute.Physical.RangeAttackSpeed(), true);
		putAttribute(new DerivedCombatAttribute.Physical.CriticalStrike(), true);
		// Spell
		putAttribute(new DerivedCombatAttribute.Spell.CastingSpeed(), true);
		putAttribute(new DerivedCombatAttribute.Spell.Regeneration.Mana(), true);
		putAttribute(new DerivedCombatAttribute.Spell.Regeneration.Rage(), true);
		putAttribute(new DerivedCombatAttribute.Spell.Regeneration.Element(), true);
		putAttribute(new DerivedCombatAttribute.Spell.Regeneration.Energy(), true);
		putAttribute(new DerivedCombatAttribute.Spell.CriticalStrike(), true);
		// Defense
		putAttribute(new DerivedCombatAttribute.Defense.Armor(), true);
		putAttribute(new DerivedCombatAttribute.Defense.Dodge(), true);
		putAttribute(new DerivedCombatAttribute.Defense.Parry(), true);
		putAttribute(new DerivedCombatAttribute.Defense.BlockRating(), true);
		putAttribute(new DerivedCombatAttribute.Defense.Adaptation(), true);
	}

	private void putAttribute(Attribute attribute, boolean override) {
		if(override) {
			attributes.put(attribute.getId(), attribute);
			return;
		}
		attributes.putIfAbsent(attribute.getId(), attribute);
	}

	// Character info
	public String getCharacterInfo(int id, String info){
		File character = getCharacterFile(id);
		YamlConfiguration data = new YamlConfiguration();
		if (data.get(info) == null){
			return "ERROR";
		} else {
			return String.valueOf(data.get(info));
		}
	}

	public String getBalance(int id){
		File character = getCharacterFile(id);
		YamlConfiguration data = new YamlConfiguration();
		int balance = data.getInt("Balance");
		int[] result = new int[3];
		int copper = balance % 100;
		balance = (balance - copper) / 100;
		int silver = balance % 100;
		int gold = (balance - silver) / 100;
		result[0] = copper;
		result[1] = silver;
		result[2] = gold;
		String c = String.valueOf(result[0]) + "\ueff3 ";
		String s = String.valueOf(result[1]) + "\ueff2 ";
		String g = String.valueOf(result[2]) + "\ueff1 ";
		return g + s + c;
	}

	// Quests
	public void addTracker(String quest, QuestProgressionTracker<?> tracker) {
		questTrackers.put(quest, tracker);
	}

	public void removeTracker(String id) {
		questTrackers.remove(id);
	}

	public enum Class {
		WARRIOR, MAGE, HUNTER, DRUID, ROGUE, PRIEST, PALADIN, NONE;

		public static Class get(String value) {
			try {
				return Class.valueOf(value);
			}catch (IllegalArgumentException ignored) {}
			return NONE;
		}
	}

}
