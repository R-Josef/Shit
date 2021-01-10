package moe.feo.shit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import dev.lone.itemsadder.api.ItemsAdder;
import moe.feo.shit.config.Config;

public class ToiletBreakListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onTest(EntityDamageEvent e) {
		// 如果事件已经被取消就直接返回，以免出现刷物品Bug
		if (e.isCancelled()) {
			return;
		}
		Entity entity = e.getEntity();
		if (e.getEntity() instanceof ArmorStand) {// 是不是盔甲架
			ArmorStand armorstand = (ArmorStand) e.getEntity();
			if (ItemsAdder.isFurniture(e.getEntity())) {// 是不是家具
				String customname = ItemsAdder.getCustomItemName(armorstand.getEquipment().getHelmet());
				if (customname.equals(Config.TOILETID.getString())) {// 是不是马桶
					if (e.getDamage() == 0) {
						UUID uuid = entity.getUniqueId();
						Toilet toilet = ToiletManager.activetoilets.get(uuid);// 尝试从活动列表获取一个马桶
						if (toilet == null) {// 说明没有玩家正在打开这个马桶
							toilet = new Toilet(uuid, entity);// 用实体里的数据创建一个带库存的马桶
							ToiletManager.activetoilets.put(uuid, toilet);// 将马桶存入活动马桶map
						}
						World word = e.getEntity().getWorld();
						Location location = e.getEntity().getLocation();
						for (ItemStack item : toilet.getInventory().getContents()) {// 遍历库存然后掉落
							if (item == null) {
								continue;
							}
							word.dropItem(location, item);
						}
						ToiletManager.activetoilets.remove(uuid);
					}

				}
			}
		}

	}

}
