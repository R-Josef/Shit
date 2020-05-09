package moe.feo.shit;

import org.bukkit.plugin.java.JavaPlugin;

public class Shit extends JavaPlugin {
	private static Shit shit;
	public static ToiletManager toiletmanager;
	public static FoodLevelListener foodlevellistener;
	
	@Override
	public void onEnable() {
		shit = this;
		this.saveDefaultConfig();
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
