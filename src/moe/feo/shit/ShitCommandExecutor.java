package moe.feo.shit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import moe.feo.shit.config.Config;
import moe.feo.shit.config.Language;

public class ShitCommandExecutor implements TabExecutor {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			List<String> list = new ArrayList<String>();
			String arg = args[0].toLowerCase();
			if ("reload".startsWith(arg)) {
				list.add("reload");
			}
			return list;
		}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		switch (args[0].toLowerCase()) {
		case "help": {
			sender.sendMessage(Language.COMMAND_PREFIX.getString() + Language.COMMAND_HELP_TITLE.getString());
			sender.sendMessage(Language.COMMAND_PREFIX.getString() + Language.COMMAND_HELP_HELP.getString());
			if (sender.hasPermission("shit.reload")) {
				sender.sendMessage(Language.COMMAND_PREFIX.getString() + Language.COMMAND_HELP_RELOAD.getString());
			}
			return true;
		}
		case "reload": {
			if (!(sender.hasPermission("shit.reload"))) {
				sender.sendMessage(Language.COMMAND_PREFIX.getString() + Language.COMMAND_NOPERMISSION.getString());
				return false;
			}
			Shit.getInstance().saveDefaultConfig();
			Config.load();
			Language.saveDefaultConfig();
			Language.load(Config.LANG.getString());
			sender.sendMessage(Language.COMMAND_PREFIX.getString() + Language.COMMAND_RELOADED.getString());
			return true;
		}
		}
		return false;
	}

}
