package uyora.core.character;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CharacterManager {

	private final Map<Player, UyoraCharacter> characterMap = new HashMap<>();

	public UyoraCharacter loadCharacter(Player player) {
		UyoraCharacter character = new UyoraCharacter(player);
		characterMap.put(player, character);
		return character;
	}

	public void unloadCharacter(Player player) {
		characterMap.remove(player);
	}

	public UyoraCharacter getUyoraCharacter(Player player) {
		return characterMap.get(player);
	}

}
