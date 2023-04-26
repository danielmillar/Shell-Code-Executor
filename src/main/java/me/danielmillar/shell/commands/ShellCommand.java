package me.danielmillar.shell.commands;

import me.danielmillar.shell.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ShellCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (!commandSender.hasPermission("shell.execute")) {
			commandSender.sendMessage(Utils.colorize("<red>You do not have permission to execute this command."));
			return true;
		}
		
		if(strings.length == 0){
			commandSender.sendMessage(Utils.colorize("<gray>Please provide a shell command."));
			return true;
		}
		
		boolean outputToConsole = false;
		if ("-c".equalsIgnoreCase(strings[0])) {
			outputToConsole = true;
			strings = Arrays.copyOfRange(strings, 1, strings.length);
		} else if ("-u".equalsIgnoreCase(strings[0])) {
			strings = Arrays.copyOfRange(strings, 1, strings.length);
		}
		
		if (strings.length == 0) {
			commandSender.sendMessage(Utils.colorize("<gray>Please provide a shell command."));
			return false;
		}
		
		Utils.executeShell(commandSender, Arrays.asList(strings), outputToConsole);
		return true;
	}
	
}
