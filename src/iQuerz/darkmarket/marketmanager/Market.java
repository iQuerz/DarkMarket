package iQuerz.darkmarket.marketmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.economy.Economy;

public class Market {
	ItemStack book1;
	ItemStack book2;
	ItemStack spawner;
	ShopHandler shop;
	
	public Market(Player player) {
		book1 = new ItemStack(Material.ENCHANTED_BOOK);
		book2 = new ItemStack(Material.ENCHANTED_BOOK);
		spawner = new ItemStack(Material.SPAWNER);
		
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book1.getItemMeta();
		Enchantment e = getRandomEnch();
		int lvl = getRandomEnchLvl(e);
		int price1 = lvl%1000;
		lvl/=1000;
		meta.addStoredEnchant(e,lvl, true);
		meta.setDisplayName("$"+Integer.toString(price1)+"k");
		book1.setItemMeta(meta);
		
		
		meta = (EnchantmentStorageMeta)book2.getItemMeta();
		e = getRandomEnch();
		lvl = getRandomEnchLvl(e);
		int price2 = lvl%1000;
		lvl/=1000;
		meta.addStoredEnchant(e,lvl, true);
		meta.setDisplayName("$"+Integer.toString(price2)+"k");
		book2.setItemMeta(meta);
		
		SpawnEgg spawnegg = generateSpawner();
		spawner = spawnegg.item1;
		ItemMeta meta1 = spawner.getItemMeta();
		meta1.setDisplayName("§e$"+spawnegg.itemPrice/1000+"k");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Spawn egg, type:");
		lore.add("§e"+spawnegg.item1.getType().toString().subSequence(0, spawnegg.item1.getType().toString().length()-10).toString().replace("_", " "));
		lore.add("§7Click with an egg on a spawner to transform it.");
		meta1.setLore(lore);
		spawner.setItemMeta(meta1);
		
		shop = new ShopHandler(book1,book2,spawner,price1*1000,price2*1000,spawnegg.itemPrice);
	}
	public void openShop(UUID id) {
		shop.openShop(id);
	}
	public void onInventoryClick(InventoryClickEvent event, Economy economy) {
    	shop.onInventoryClick(event, economy);
    }
	
	private Enchantment getRandomEnch() {
		Random r = new Random();
		int ench = r.nextInt(15);
		
		switch (ench){
		case 0:{
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		}
		case 1:{
			return Enchantment.PROTECTION_FIRE;
		}
		case 2:{
			return Enchantment.PROTECTION_EXPLOSIONS;
		}
		case 3:{
			return Enchantment.PROTECTION_FALL;
		}
		case 4:{
			return Enchantment.OXYGEN;
		}
		case 5:{
			return Enchantment.DAMAGE_ALL;
		}
		case 6:{
			return Enchantment.ARROW_DAMAGE;
		}
		case 7:{
			return Enchantment.DAMAGE_UNDEAD;
		}
		case 8:{
			return Enchantment.DAMAGE_ARTHROPODS;
		}
		case 9:{
			return Enchantment.IMPALING;
		}
		case 10:{
			return Enchantment.PIERCING;
		}
		case 11:{
			return Enchantment.QUICK_CHARGE;
		}
		case 12:{
			return Enchantment.DURABILITY;
		}
		case 13:{
			return Enchantment.DIG_SPEED;
		}
		case 14:{
			return Enchantment.PROTECTION_PROJECTILE;
		}
		case 15:{
			return Enchantment.LOOT_BONUS_MOBS;
		}
		default:{
			return Enchantment.THORNS;
		}
		}
	}
	private int getRandomEnchLvl(Enchantment ench) {
		Random r = new Random();
		if(ench.equals(Enchantment.DIG_SPEED)||ench.equals(Enchantment.DAMAGE_ALL)||ench.equals(Enchantment.ARROW_DAMAGE)||ench.equals(Enchantment.DAMAGE_UNDEAD)||ench.equals(Enchantment.DAMAGE_ARTHROPODS)||ench.equals(Enchantment.IMPALING)) {
			switch(r.nextInt(2)) {
			case 0:{
				return 6*1000+r.nextInt(50)+250;
			}
			case 1:{
				return 7*1000+r.nextInt(100)+500;
			}
			default:{
				return 6000;
			}
			}
		}
		else if(ench.equals(Enchantment.DURABILITY)||ench.equals(Enchantment.OXYGEN)||ench.equals(Enchantment.LOOT_BONUS_MOBS)) {
			switch(r.nextInt(2)) {
			case 0:{
				return 4*1000+r.nextInt(50)+250;
			}
			case 1:{
				return 5*1000+r.nextInt(100)+500;
			}
			default:{
				return 4;
			}
			}
		}
		else if(ench.equals(Enchantment.PROTECTION_ENVIRONMENTAL)||ench.equals(Enchantment.PROTECTION_FIRE)||ench.equals(Enchantment.PROTECTION_PROJECTILE)||ench.equals(Enchantment.PIERCING)||ench.equals(Enchantment.PROTECTION_EXPLOSIONS)) {
			switch(r.nextInt(2)) {
			case 0:{
				return 5*1000+r.nextInt(50)+250;
			}
			case 1:{
				return 6*1000+r.nextInt(100)+500;
			}
			default:{
				return 5;
			}
			}
		}
		else if(ench.equals(Enchantment.PROTECTION_FALL)) {
			return 5*1000+r.nextInt(50)+250;
		}
		else if(ench.equals(Enchantment.QUICK_CHARGE)) {
			return 4*1000+r.nextInt(50)+250;
		}
		else return 1000;
	}

	private SpawnEgg generateSpawner() {
		SpawnEgg spawner = new SpawnEgg();
		Random r = new Random();
		int spwnr = r.nextInt(13);
		switch (spwnr){
			case 0:{
				spawner.item1 = new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG);
				spawner.itemPrice = 1000000;
				break;
				}
			case 1:{
				spawner.item1 = new ItemStack(Material.GUARDIAN_SPAWN_EGG);
				spawner.itemPrice = 1000000;
				break;
				}
			case 2:{
				spawner.item1 = new ItemStack(Material.VINDICATOR_SPAWN_EGG);
				spawner.itemPrice = 1000000;
				break;
			}
			case 3:{
				spawner.item1 =  new ItemStack(Material.GHAST_SPAWN_EGG);
				spawner.itemPrice = 1000000;
				break;
			}
			case 4:{
				spawner.item1 = new ItemStack(Material.SHEEP_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 5:{
				spawner.item1 = new ItemStack(Material.COW_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 6:{
				spawner.item1 = new ItemStack(Material.MOOSHROOM_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 7:{
				spawner.item1 = new ItemStack(Material.SQUID_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 8:{
				spawner.item1 = new ItemStack(Material.DROWNED_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 9:{
				spawner.item1 = new ItemStack(Material.SLIME_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 10:{
				spawner.item1 = new ItemStack(Material.WITCH_SPAWN_EGG);
				spawner.itemPrice = 800000;
				break;
			}
			case 11:{
				spawner.item1 = new ItemStack(Material.ZOMBIE_PIGMAN_SPAWN_EGG);
				spawner.itemPrice = 650000;
				break;
			}
			case 12:{
				spawner.item1 = new ItemStack(Material.MAGMA_CUBE_SPAWN_EGG);
				spawner.itemPrice = 650000;
				break;
			}
			default:{
				spawner.item1 = new ItemStack(Material.CREEPER_SPAWN_EGG);
				spawner.itemPrice = 1000000;
				break;
			}
		}
		return spawner;
	}
	
	class SpawnEgg{
		ItemStack item1;
		int itemPrice;
	};
}
