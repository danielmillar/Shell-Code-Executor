package me.danielmillar.shell;

import me.danielmillar.shell.commands.ShellCommand;
import me.danielmillar.shell.listeners.TabCompleteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shell extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new TabCompleteListener(), this);
        getCommand("shell").setExecutor(new ShellCommand());
    }

}
