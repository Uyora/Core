package uyora.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uyora.core.Main;

public class CharacterCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender instanceof Player))
			return false;
		Player player = (Player) commandSender;
		if (!player.hasPermission("uyora.admin") || !command.getName().equalsIgnoreCase("character"))
			return false;
		if(args.length > 0) {
			switch (args[0]) {
				case "create":
					Main.core.getCharacterManager().getUyoraCharacter(player).startCharacterCreation();
					return true;
				case "set":
					setCharacter(player, args);
					return true;
			}
		}
		return false;
	}

	private void setCharacter(Player player, String[] args) {
		if(args.length <= 1)
			return;
		int id = Integer.parseInt(args[1]);
		Main.core.getCharacterManager().getUyoraCharacter(player).switchCharacter(id);
	}

}
