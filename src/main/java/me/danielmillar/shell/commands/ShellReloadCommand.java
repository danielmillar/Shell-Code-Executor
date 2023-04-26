package me.danielmillar.shell.commands;

import me.danielmillar.shell.Shell;
import me.danielmillar.shell.utils.ConfigKeys;
import me.danielmillar.shell.utils.Utils;
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
			commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.NO_PERMISSION)));
			return true;
		}
		
		plugin.getConfigManager().reloadConfig();
		commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.SHELL_RELOAD_SUCCESS)));
		return true;
	}
}
