package iQuerz.darkmarket.marketmanager;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.milkbowl.vault.economy.Economy;

public class MarketManager {
	HashMap<UUID, Market> markets;
	
	public MarketManager() {
		markets = new HashMap<UUID, Market>();
	}
	
	//checks if player exists in managers player list
	private boolean checkPlayer(Player player) {
		if(markets.get(player.getUniqueId())!=null)
			return false;
		
		return true;
	}
	
	//<summary>
	//checks if Player player exists in Manager's player list, if not, adds a new market for the player.
	//</summary>
	public void addMarket(Player player) {
		if(checkPlayer(player)) {
		Market market = new Market(player);
		markets.put(player.getUniqueId(), market);
		}
	}
	public void openShop(UUID id) {
		Market mrk = markets.get(id);
		if(mrk == null)
			Bukkit.broadcastMessage("kure");
		markets.get(id).openShop(id);
	}
	public void onInventoryClick(InventoryClickEvent event, UUID id, Economy economy) {
    	markets.get(id).onInventoryClick(event, economy);
    }
}