package iQuerz.darkmarket.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import iQuerz.darkmarket.main.DarkMarket;


public class Listeners implements Listener {
	DarkMarket plugin;
	public Listeners(DarkMarket plugin) {
		this.plugin = plugin;
	}
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "! Did you know Obama's last name is Care?");
        plugin.getManager().addMarket(event.getPlayer());
    }
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock().getLocation().equals(new Location(event.getClickedBlock().getLocation().getWorld(),-46,63,260,0,0))) {
			plugin.getManager().openShop(event.getPlayer());
		}
	}
}