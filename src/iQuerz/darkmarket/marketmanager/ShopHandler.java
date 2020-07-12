package iQuerz.darkmarket.marketmanager;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import iQuerz.darkmarket.main.DarkMarket;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class ShopHandler{

	private Inventory inv;
	private ItemStack book1;
	private ItemStack book2;
	private ItemStack spawner;
	private double price1;
	private double price2;
	private double price3;
	Player player;
	DarkMarket plugin;
	double cost;

	public ShopHandler(ItemStack _book1, ItemStack _book2, ItemStack _spawner, double price1, double price2, double price3)
    {
        inv = Bukkit.createInventory(null, 9, "Dark Market");
        book1 = _book1;
        book2 = _book2;
        spawner = _spawner;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        initializeItems();
    }
	
	public void initializeItems()
    {
		inv.addItem(createGuiItem(Material.WHITE_STAINED_GLASS_PANE,"       ",""));
		inv.addItem(createGuiItem(Material.WHITE_STAINED_GLASS_PANE," ",""));
		inv.addItem(createGuiItem(Material.WHITE_STAINED_GLASS_PANE,"  ",""));
		inv.addItem(book1);
		inv.addItem(book2);
		inv.addItem(spawner);
        inv.addItem(createGuiItem(Material.WHITE_STAINED_GLASS_PANE,"   ",""));
        inv.addItem(createGuiItem(Material.WHITE_STAINED_GLASS_PANE,"    ",""));
        inv.addItem(createGuiItem(Material.WHITE_STAINED_GLASS_PANE,"     ",""));
    }
	protected ItemStack createGuiItem(Material material, String name, String... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
	
    public void openShop(UUID id)
    {
    	inv.clear();
    	initializeItems();
        Bukkit.getPlayer(id).openInventory(inv);
    }
    
    public void onInventoryClick(InventoryClickEvent event, Economy economy) {
    	if(!event.getInventory().equals(inv))
    		return;
    	event.setCancelled(true);
    	if(event.getClickedInventory().equals(inv)) {
    		if(event.getRawSlot() == 1||event.getRawSlot() == 2||event.getRawSlot() == 6||event.getRawSlot() == 7||event.getRawSlot() == 8||event.getRawSlot() == 0) {
    			return;
    		}
    		EnchantmentStorageMeta meta = null;
    		switch(event.getRawSlot()) {
	    		case 3:
	    			cost = price1;
	    			meta = (EnchantmentStorageMeta)book1.getItemMeta();
	    			break;
	    		case 4:
	    			cost = price2;
	    			meta = (EnchantmentStorageMeta)book2.getItemMeta();
	    			break;
	    		case 5:
	    			cost = price3;
	    			break;
    		}
    		Player p = Bukkit.getPlayer(event.getWhoClicked().getUniqueId());
    		final EconomyResponse response = economy.withdrawPlayer(p, cost);
    		
    		if (response.transactionSuccess()) {
	    		p.sendMessage("§6$"+cost + " taken from your account.");
	    		if(event.getRawSlot()<5) {
		    		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		    		String name = book.getItemMeta().getDisplayName();
		    		meta.setDisplayName(name);
		    		book.setItemMeta(meta);
		    		p.getInventory().addItem(book);
	    		}
	    		else {
	    			ItemStack newSpawner = new ItemStack(this.spawner.getType());
	    			p.getInventory().addItem(newSpawner);
	    		}
    		}
    		else {
	    		p.sendMessage("§6You could not afford to do this");
	    			event.setCancelled(true);
    		}
    	}
    }
}
