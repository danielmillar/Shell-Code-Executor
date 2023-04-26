package me.danielmillar.shell;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
	
	public static Component colorize(String string) {
		MiniMessage miniMessage = MiniMessage.miniMessage();
		return miniMessage.deserialize(string);
	}
	
	public static List<Component> colorize(List<String> strings) {
		MiniMessage miniMessage = MiniMessage.miniMessage();
		return strings.stream().map(miniMessage::deserialize).collect(Collectors.toList());
	}
	
	public static void executeShell(CommandSender commandSender, List<String> args, boolean outputToConsole){
		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command(args);
			Process process = processBuilder.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if(outputToConsole) {
					Bukkit.getConsoleSender().sendMessage(Utils.colorize(line));
				}else {
					commandSender.sendMessage(Utils.colorize(line));
				}
			}
			
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				if(outputToConsole) {
					Bukkit.getConsoleSender().sendMessage(Utils.colorize("<gray>Command exited with code <green>" + exitCode));
				}else {
					commandSender.sendMessage(Utils.colorize("<gray>Command exited with code <green>" + exitCode));
				}
			}
			
			if(outputToConsole) {
				commandSender.sendMessage(Utils.colorize("<gray>Command executed and sent output to console. Please check console for output."));
			}
			
		} catch (IOException | InterruptedException e) {
			commandSender.sendMessage(Utils.colorize("<red>Error: <gray>Failed to execute the command. Please check console for error details."));
			Bukkit.getLogger().warning(e.getMessage());
		}
	}
}
