package me.danielmillar.shell.managers;

import lombok.Getter;
import me.danielmillar.shell.Shell;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ConfigManager {
	
	private Shell plugin;
	
	@Getter
	private static final ConcurrentHashMap<String, ConfigManager> configs = new ConcurrentHashMap<>();
	
	private final String name;
	private final String path;
	
	private File configFile;
	private FileConfiguration config;
	
	public ConfigManager(Shell plugin, String name){
		this.plugin = plugin;
		this.name = name;
		this.path = plugin.getDataFolder().getAbsolutePath();
	}
	
	public void createConfig() {
		configFile = new File(path, name + ".yml");
		
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			plugin.saveResource(name + ".yml", false);
		}
		
		config = new YamlConfiguration();
		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reloadConfig() {
		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
