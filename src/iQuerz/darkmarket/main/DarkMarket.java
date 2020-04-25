package iQuerz.darkmarket.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import iQuerz.darkmarket.listeners.Listeners;
import iQuerz.darkmarket.marketmanager.MarketManager;
import net.milkbowl.vault.economy.Economy;

public class DarkMarket extends JavaPlugin{
	
	MarketManager manager;
	Economy economy;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp != null) {
            economy = rsp.getProvider();
        }
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
	public Economy getEconomy() {
		return this.economy;
	}
}
