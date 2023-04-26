package me.danielmillar.shell;

import lombok.Getter;
import me.danielmillar.shell.commands.ShellBookCommand;
import me.danielmillar.shell.commands.ShellCommand;
import me.danielmillar.shell.commands.ShellReloadCommand;
import me.danielmillar.shell.listeners.BookEditListener;
import me.danielmillar.shell.listeners.TabCompleteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shell extends JavaPlugin {
    
    @Getter
    private static Shell inst;
    
    public Shell(){
        inst = this;
    }

    @Override
    public void onEnable() {
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        Bukkit.getPluginManager().registerEvents(new TabCompleteListener(), this);
        Bukkit.getPluginManager().registerEvents(new BookEditListener(this), this);
        getCommand("shell").setExecutor(new ShellCommand());
        getCommand("shellbook").setExecutor(new ShellBookCommand(this));
        getCommand("shellreload").setExecutor(new ShellReloadCommand(this));
    }

}
