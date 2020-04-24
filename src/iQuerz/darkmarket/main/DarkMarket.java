package iQuerz.darkmarket.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import iQuerz.darkmarket.listeners.Listeners;
import iQuerz.darkmarket.marketmanager.MarketManager;

public class DarkMarket extends JavaPlugin{
MarketManager manager;
	@Override
	public void onEnable() {
		manager = new MarketManager();
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		Bukkit.getServer().getLogger().info("Enabled " + getDescription().getName() + " v" + getDescription().getVersion() +".");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getServer().getLogger().info("Disabled " + getDescription().getName() + " v" + getDescription().getVersion() +".");
	}
	
	public MarketManager getManager() {
		return manager;
	}
}
