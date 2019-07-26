package com.xarmor;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.xarmor.configs.ConfigManager;
import com.xarmor.events.InvClick;

public class XArmor extends JavaPlugin {
	public static final int BASE_DURATION = 40;
	public static final int INFINITY = 20 * 60 * 60;
	
	public ConfigManager mainConfig = new ConfigManager(getDataFolder(), "config");
	
	@Override
	public void onEnable() {
		registerEvents(getServer().getPluginManager());
		initMainConfig();
	}
	
	private void initMainConfig() {
		Configuration config = mainConfig.getConfig();
		
		if (!config.isSet("ignorePermissions")) {
			config.set("ignorePermissions", false);
		}
		
		mainConfig.saveConfig();
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new InvClick(), this);
	}
}
