package moe.feo.shit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Toilet {
	
	private UUID uuid;
	@SuppressWarnings("unused")
	private Entity entity;
	private Inventory inv;
	private PersistentDataContainer container;
	private NamespacedKey namespacekey = new NamespacedKey(Shit.getInstance(), "Toliet-Inv");

	
	public Toilet(UUID uuid, Entity entity) {
		this.uuid = uuid;
		this.entity = entity;
		container = entity.getPersistentDataContainer();// 从实体中得到容器
		inv = Bukkit.createInventory(new TolietInventoryHolder(), InventoryType.HOPPER, Shit.getInstance().getConfig().getString("toiletname"));// 创建一个库存
		restoreInventory();
	}

	class TolietInventoryHolder implements InventoryHolder {
		@Override
		public Inventory getInventory() {
			return inv;
		}
		public UUID getUUID() {
			return uuid;
		}
	}
	
	public Inventory getInventory() {
		return inv;
	}

	public void restoreInventory() {
		String itemsdata = container.get(namespacekey, PersistentDataType.STRING);// 获取数据
		if (itemsdata != null) {// 如果数据不是空的
			YamlConfiguration yamlconfig = new YamlConfiguration();
			try {
				yamlconfig.loadFromString(itemsdata);
				for (int i=0; i < inv.getSize(); i++) {
					String index = String.valueOf(i);
					ItemStack item =  yamlconfig.getItemStack(index);
					if (item != null) {
						inv.setItem(i, item);
					}
				}
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveInventory() {
		ItemStack[] items = inv.getContents();
		 YamlConfiguration yamlconfig = new YamlConfiguration();
		for (int i = 0; i < items.length; i++) {
			ItemStack item = items[i];
			String index = String.valueOf(i);
			 yamlconfig.set(index, item);
		}
		container.set(namespacekey, PersistentDataType.STRING, yamlconfig.saveToString());// 将数据写入容器
	}
	
	public UUID getUUID() {
		return this.uuid;
	}

}
