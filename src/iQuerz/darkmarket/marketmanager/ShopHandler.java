package iQuerz.darkmarket.marketmanager;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.economy.Economy;

public class ShopHandler implements InventoryHolder, Listener {

	private Inventory inv;
	private ItemStack book1;
	private ItemStack book2;
	private ItemStack spawner;

	public ShopHandler(ItemStack _book1, ItemStack _book2, ItemStack _spawner)
    {
        inv = Bukkit.createInventory(this, 9, "Dark Market");
        book1 = _book1;
        book2 = _book2;
        spawner = _spawner;
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
	
    public void openShop(Player p)
    {
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if (e.getInventory().getHolder() != this) return;

        e.setCancelled(true);

        //ItemStack clickedItem = e.getCurrentItem();
    }

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}
}
