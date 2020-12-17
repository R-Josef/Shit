package moe.feo.shit;

import org.bukkit.plugin.java.JavaPlugin;

import moe.feo.shit.config.Config;
import moe.feo.shit.config.Language;

public class Shit extends JavaPlugin {
	private static Shit shit;
	public static ToiletManager toiletmanager;
	public static FoodLevelListener foodlevellistener;
	
	@Override
	public void onEnable() {
		shit = this;
		this.saveDefaultConfig();
		Config.load();
		Language.saveDefaultConfig();
		Language.load(Config.LANG.getString());
		ShitCommandExecutor cmdexecutor = new ShitCommandExecutor();
		this.getCommand("shit").setExecutor(cmdexecutor);
		this.getCommand("shit").setTabCompleter(cmdexecutor);
		getServer().getPluginManager().registerEvents(foodlevellistener = new FoodLevelListener(), this);
		getServer().getPluginManager().registerEvents(new ToiletClickListener(), this);
		getServer().getPluginManager().registerEvents(toiletmanager = new ToiletManager(), this);
		getServer().getPluginManager().registerEvents(new ToiletBreakListener(), this);
		
	}
	@Override
	public void onDisable() {
	}
	
	public static Shit getInstance() {
		return shit;
	}
}
