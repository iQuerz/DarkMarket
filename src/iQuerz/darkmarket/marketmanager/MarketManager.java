package iQuerz.darkmarket.marketmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class MarketManager {
	List<Player> players;
	HashMap<UUID, Market> markets;
	
	public MarketManager() {
		players = new ArrayList<>();
		markets = new HashMap<UUID, Market>();
	}
	
	//checks if player exists in managers player list
	private boolean checkPlayer(Player player) {
		for(Player p : players) {
			if(player.equals(p));
				return false;
		}
		players.add(player);
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
	public void openShop(Player player) {
		markets.get(player.getUniqueId()).openShop();
	}
}