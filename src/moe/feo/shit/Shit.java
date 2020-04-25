package moe.feo.shit;

import org.bukkit.plugin.java.JavaPlugin;

public class Shit extends JavaPlugin {
	private static Shit shit;
	
	@Override
	public void onEnable() {
		shit = this;
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new FoodLevelListener(), this);
	}
	@Override
	public void onDisable() {
		
	}
	
	public static Shit getInstance() {
		return shit;
	}
}
