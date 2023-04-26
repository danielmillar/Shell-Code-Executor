package me.danielmillar.shell;

import lombok.Getter;
import me.danielmillar.shell.commands.ShellBookCommand;
import me.danielmillar.shell.commands.ShellCommand;
import me.danielmillar.shell.commands.ShellReloadCommand;
import me.danielmillar.shell.listeners.BookEditListener;
import me.danielmillar.shell.listeners.TabCompleteListener;
import me.danielmillar.shell.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Shell extends JavaPlugin {
    
    @Getter
    private static Shell inst;
    
    public Shell(){
        inst = this;
    }
    
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        
        configManager = new ConfigManager(this, "config");
        configManager.createConfig();
        
        Bukkit.getPluginManager().registerEvents(new TabCompleteListener(), this);
        Bukkit.getPluginManager().registerEvents(new BookEditListener(this), this);
        getCommand("shell").setExecutor(new ShellCommand(this));
        getCommand("shellbook").setExecutor(new ShellBookCommand(this));
        getCommand("shellreload").setExecutor(new ShellReloadCommand(this));
    }

}
