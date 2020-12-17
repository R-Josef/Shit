package moe.feo.shit;

import java.util.UUID;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import dev.lone.itemsadder.api.ItemsAdder;
import moe.feo.shit.config.Config;
import moe.feo.shit.config.Language;

public class ToiletClickListener implements Listener {
	
	@EventHandler
	public void onTolietClick(PlayerInteractAtEntityEvent e) {
		Entity entity = e.getRightClicked();
		if (ItemsAdder.isFurniture(entity)) {// 判断是否是家具
			ArmorStand armorstand = (ArmorStand) entity;// ia的家具全是盔甲架
			ItemStack item = armorstand.getEquipment().getHelmet();
			String itemname = ItemsAdder.getCustomItemName(item);
			Player player = e.getPlayer();
			if (itemname.equals(Config.TOILETID.getString())) {// 判断是否为马桶
				boolean open = player.isSneaking();// 如果是shit右键则是打开马桶
				UUID uuid = entity.getUniqueId();
				Toilet toilet = ToiletManager.activetoilets.get(uuid);// 尝试从活动列表获取一个马桶
				if (toilet == null) {// 说明没有玩家正在打开这个马桶
						toilet = new Toilet(uuid, entity);// 用实体里的数据创建一个带库存的马桶
						ToiletManager.activetoilets.put(uuid, toilet);// 将马桶存入活动马桶map
				}
				if (open) {// 玩家打开马桶
					e.setCancelled(true);
					player.openInventory(toilet.getInventory());
				} else {// 玩家开始排便
					if (!Shit.foodlevellistener.playerfoodlost.containsKey(player.getUniqueId())) {// 如果之前没有这个玩家的记录
						Shit.foodlevellistener.playerfoodlost.put(player.getUniqueId(), 0);
					}
					int levellostnow = Shit.foodlevellistener.playerfoodlost.get(player.getUniqueId());// 饱食度减少的值
					int shitvalue = Config.POOPWHENFOODLOST.getInt();// 减少多少饱食度拉一坨屎
					int count = 0;
					while (levellostnow >= shitvalue) {// 坐在马桶上就能把屎全部拉出来
						// 这里对库存进行操作，但是没有view的entity
					// ToiletManager无法检测到view就会保存库存并且从活动库存map中清除
					// 但是mc操作是单线程，所以应该没事
						poop(toilet.getInventory());
						levellostnow = levellostnow - shitvalue;
						count++;
					}
					Shit.foodlevellistener.playerfoodlost.put(player.getUniqueId(), levellostnow);
					if (count > 0) {
						player.sendMessage(Language.MESSAGE_POOPONTOILET.getString().replaceAll("%NUM%", String.valueOf(count)));
					} else {
						player.sendMessage(Language.MESSAGE_NOPOOPONTOILET.getString());
					}
					
					if (toilet.getInventory().getViewers().size() <= 0) {// 没有玩家正在查看马桶了
						toilet.saveInventory();
						ToiletManager.activetoilets.remove(uuid);// 清空活动马桶
					}
					
				}
			}
		}
		
	}
	
	public void poop(Inventory inv) {
		ItemStack shit = ItemsAdder.getCustomItem(Config.SHITID.getString());
		inv.addItem(shit);
	}

}
