package moe.feo.shit.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import moe.feo.shit.Shit;

public enum Config {

	LANG("lang"), POOPWHENFOODLOST("poopwhenfoodlost"), POOPCANSAVE("poopcansave"), WHITELIST("whitelist"),
	TOILETID("toiletid"), SHITID("shitid");

	private static File file;
	private static FileConfiguration config;
	private String path;

	Config(String path) {
		this.path = path;
	}

	public static void load() {
		if (file == null) {
			file = new File(Shit.getInstance().getDataFolder(), "config.yml");
		}
		config = YamlConfiguration.loadConfiguration(file);// 用这个方法加载配置可以解决编码问题
		try (Reader reader = new InputStreamReader(Shit.getInstance().getResource("config.yml"),
				StandardCharsets.UTF_8)) {// 读取默认配置
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(reader);
			config.setDefaults(defConfig);// 设置默认
		} catch (IOException ioe) {
			Shit.getInstance().getLogger().log(Level.SEVERE, "Error when reading default config!", ioe);
		}
	}

	public String getString() {
		return config.getString(path);
	}

	public int getInt() {
		return config.getInt(path);
	}

	public List<String> getStringList() {
		return config.getStringList(path);
	}
}