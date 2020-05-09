package moe.feo.shit;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

import moe.feo.shit.Toilet.TolietInventoryHolder;

public class ToiletManager implements Listener {// 厕所管理员(厕所所长)
	
	public static HashMap<UUID, Toilet> activetoilets = new HashMap<UUID, Toilet>();// 活动马桶map

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		InventoryHolder holder = event.getPlayer().getOpenInventory().getTopInventory().getHolder();
		if (holder instanceof Toilet.TolietInventoryHolder) {// 确认是本插件的库存
			TolietInventoryHolder toiletholder = (TolietInventoryHolder) holder;
			if (event.getViewers().size() <= 1) {// 只剩这一个玩家查看库存了, 则开始保存
				UUID uuid = toiletholder.getUUID();
				Toilet toilet = activetoilets.get(uuid);// 获得目标马桶对象
				toilet.saveInventory();
				activetoilets.remove(uuid);// 清空活动马桶
			}
		}
	}
	
	public void saveInventory(UUID uuid) {
		
	}
}
