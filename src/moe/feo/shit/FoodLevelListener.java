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
import moe.feo.shit.config.Config;
import moe.feo.shit.config.Language;

public class FoodLevelListener implements Listener {
	public Map<UUID, Integer> playerfoodlost = new HashMap<UUID, Integer>();
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e){
		UUID uuid = e.getEntity().getUniqueId();
		Player player = Bukkit.getPlayer(uuid);
		
		List<String> whitelist = Config.WHITELIST.getStringList();
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
			int shitvalue = Config.POOPWHENFOODLOST.getInt();// 减少多少饱食度拉一坨屎
			int shitsave = Config.POOPCANSAVE.getInt();// 可以屯多少屎
			while (levellostnow > shitvalue*shitsave) {// 当屎大于可以储存的极限时
				World word = e.getEntity().getWorld();
				Location location = e.getEntity().getLocation();
				poop(word, location);//拉屎...
				player.sendMessage(Language.MESSAGE_POOP.getString());
				levellostnow = levellostnow - shitvalue;
			}
			playerfoodlost.put(uuid, levellostnow);
			if (levellostnow > shitvalue * (shitsave - 1)) {// 如果还差一坨就要憋不住了
				player.sendMessage(Language.MESSAGE_CANTKEEP.getString());
			}
		}
	}
	
	public void poop(World word, Location location) {
		ItemStack shit = ItemsAdder.getCustomItem(Config.SHITID.getString());
		word.dropItem(location, shit);
	}

}
