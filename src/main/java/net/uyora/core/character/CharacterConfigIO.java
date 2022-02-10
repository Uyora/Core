package net.uyora.core.character;

import org.bukkit.configuration.ConfigurationSection;

public interface CharacterConfigIO {

	void save(ConfigurationSection section);
	void load(ConfigurationSection section);

}
