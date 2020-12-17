package moe.feo.shit.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import moe.feo.shit.Shit;

public enum Language {

	TOILETNAME("toiletname"), MESSAGE_CANTKEEP("message.cantkeep"), MESSAGE_POOP("message.poop"),
	MESSAGE_POOPONTOILET("message.poopontoilet"), MESSAGE_NOPOOPONTOILET("message.nopoopontoilet"),
	COMMAND_PREFIX("command.prefix"), COMMAND_INVALID("command.invalid"), COMMAND_NOPERMISSION("command.nopermission"),
	COMMAND_RELOADED("command.reloaded"), COMMAND_HELP_TITLE("command.help.title"),
	COMMAND_HELP_HELP("command.help.help"), COMMAND_HELP_RELOAD("command.help.reload");

	private static File file;
	private static FileConfiguration config;
	private static List<String> languages;
	private String path;

	static {
		languages = new ArrayList<String>();
		languages.add("zh_CN");
		languages.add("en_US");
	}

	Language(String path) {
		this.path = path;
	}

	public static void load(String lang) {
		if (file == null) {
			file = new File(Shit.getInstance().getDataFolder() + "lang/", lang + ".yml");
		}
		config = YamlConfiguration.loadConfiguration(file);// 用这个方法加载配置可以解决编码问题
		try (Reader reader = new InputStreamReader(Shit.getInstance().getResource("lang/" + lang + ".yml"),
				StandardCharsets.UTF_8)) {// 读取默认配置
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(reader);
			config.setDefaults(defConfig);// 设置默认
		} catch (IOException ioe) {
			Shit.getInstance().getLogger().log(Level.SEVERE, "Error when reading default config!", ioe);
		}
	}

	public static void saveDefaultConfig() {
		if (file == null) {
			file = new File(Shit.getInstance().getDataFolder(), "lang");
		}
		if (!file.exists()) {
			file.mkdir();
		}
		for (String language : languages) {
			Shit.getInstance().saveResource("lang/" + language + ".yml", false);
		}
	}

	public String getString() {
		return config.getString(path);
	}
}
