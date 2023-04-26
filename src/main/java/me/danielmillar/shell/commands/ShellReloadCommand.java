package me.danielmillar.shell.commands;

import me.danielmillar.shell.Shell;
import me.danielmillar.shell.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ShellReloadCommand implements CommandExecutor {
	
	private Shell plugin;
	
	public ShellReloadCommand(Shell plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if(!commandSender.hasPermission("shell.reload")){
			commandSender.sendMessage(Utils.colorize("<red>You do not have permission to use this command!"));
			return true;
		}
		
		plugin.getConfigManager().reloadConfig();
		commandSender.sendMessage(Utils.colorize("<green>Config reloaded!"));
		return true;
	}
}
