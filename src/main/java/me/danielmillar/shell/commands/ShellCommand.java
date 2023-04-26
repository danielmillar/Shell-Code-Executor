package me.danielmillar.shell.commands;

import me.danielmillar.shell.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShellCommand implements CommandExecutor {
	
	private static final String PERMISSION = "shell.execute";
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (!commandSender.hasPermission(PERMISSION)) {
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
		
		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command(Arrays.asList(strings));
			Process process = processBuilder.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (outputToConsole) {
					Bukkit.getConsoleSender().sendMessage(Utils.colorize(line));
				} else {
					commandSender.sendMessage(Utils.colorize(line));
				}
			}
			
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				if(outputToConsole) {
					Bukkit.getConsoleSender().sendMessage(Utils.colorize("<gray>Command exited with code <gold>" + exitCode));
				}else {
					commandSender.sendMessage(Utils.colorize("<gray>Command exited with code <gold>" + exitCode));
				}
			}
			
			if(outputToConsole) {
				commandSender.sendMessage(Utils.colorize("<gray>Command executed and sent output to console. Please check console for output."));
			}
			
			return true;
		} catch (IOException | InterruptedException e) {
			commandSender.sendMessage(Utils.colorize("<red>Error: <gray>Failed to execute the command. Please check console for error details."));
			Bukkit.getLogger().warning(e.getMessage());
		}
		
		return true;
	}
	
}
