package moe.feo.shit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import dev.lone.itemsadder.api.ItemsAdder;

public class FoodLevelListener implements Listener {
	private Map<UUID, Integer> playerfoodlost = new HashMap<UUID, Integer>();
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e){
		UUID uuid = e.getEntity().getUniqueId();
		Player player = Bukkit.getPlayer(uuid);
		
		List<String> whitelist = Shit.getInstance().getConfig().getStringList("whitelist");
		for (String listuuidstr : whitelist) {
			UUID listuuid = UUID.fromString(listuuidstr);
			if (uuid.equals(listuuid)) {
				return;// 白名单中的人不会拉屎
			}
		}
		
		int level = e.getFoodLevel();// 目前的饱食度
		int levelbefore = player.getFoodLevel();// 之前的饱食度
		int levellosting = levelbefore - level;// 此次减少的饱食度
		
		if (!playerfoodlost.containsKey(uuid)) {// 如果之前没有这个玩家的记录
			playerfoodlost.put(uuid, 0);
		}
		if (levellosting > 0) {// 表示是饱食减少
			int levellostbefore = playerfoodlost.get(uuid);// 之前总共减少的饱食度
			int levellostnow = levellostbefore + levellosting;// 目前总共减少的饱食度
			int shitvalue = Shit.getInstance().getConfig().getInt("poopwhenfoodlost");// 减少多少饱食度拉一坨屎
			while (levellostnow >= shitvalue) {
				World word = e.getEntity().getWorld();
				Location location = e.getEntity().getLocation();
				poop(word, location);//拉屎...
				player.sendMessage(Shit.getInstance().getConfig().getString("message.poop"));
				levellostnow = levellostnow - shitvalue;
			}
			playerfoodlost.put(uuid, levellostnow);
		}
	}
	
	public void poop(World word, Location location) {
		ItemStack shit = ItemsAdder.getCustomItem("yinwu:shit");
		word.dropItem(location, shit);
	}

}
