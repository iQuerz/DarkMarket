package iQuerz.darkmarket.marketmanager;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class Market {
	ItemStack book1;
	ItemStack book2;
	ItemStack spawner;
	Player player;
	ShopHandler shop;
	
	public Market(Player player) {
		book1 = new ItemStack(Material.ENCHANTED_BOOK);
		book2 = new ItemStack(Material.ENCHANTED_BOOK);
		spawner = new ItemStack(Material.SPAWNER);
		this.player = player;
		
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book1.getItemMeta();
		Enchantment e = getRandomEnch();
		meta.addStoredEnchant(e,getRandomEnchLvl(e), true);
		book1.setItemMeta(meta);
		
		meta = (EnchantmentStorageMeta)book2.getItemMeta();
		e = getRandomEnch();
		meta.addStoredEnchant(e,getRandomEnchLvl(e), true);
		book2.setItemMeta(meta);
		
		spawner = new ItemStack(Material.DROWNED_SPAWN_EGG);
		shop = new ShopHandler(book1,book2,spawner);
	}
	public void openShop() {
		shop.openShop(player);
	}
	
	private Enchantment getRandomEnch() {
		Random r = new Random();
		int ench = r.nextInt(14);
		
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
				return 6;
			}
			case 1:{
				return 7;
			}
			default:{
				return 6;
			}
			}
		}
		else if(ench.equals(Enchantment.DURABILITY)||ench.equals(Enchantment.OXYGEN)) {
			switch(r.nextInt(2)) {
			case 0:{
				return 4;
			}
			case 1:{
				return 5;
			}
			default:{
				return 4;
			}
			}
		}
		else if(ench.equals(Enchantment.PROTECTION_ENVIRONMENTAL)||ench.equals(Enchantment.PROTECTION_FIRE)||ench.equals(Enchantment.PROTECTION_PROJECTILE)||ench.equals(Enchantment.PIERCING)||ench.equals(Enchantment.PROTECTION_EXPLOSIONS)) {
			switch(r.nextInt(2)) {
			case 0:{
				return 5;
			}
			case 1:{
				return 6;
			}
			default:{
				return 5;
			}
			}
		}
		else if(ench.equals(Enchantment.PROTECTION_FALL)) {
			return 5;
		}
		else if(ench.equals(Enchantment.QUICK_CHARGE)) {
			return 4;
		}
		else return 1000;
	}
}
