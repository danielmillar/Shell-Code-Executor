package me.danielmillar.shell.utils;

import me.danielmillar.shell.Shell;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
	
	private static Shell plugin = Shell.getInst();
	
	public static Component colorize(String string) {
		MiniMessage miniMessage = MiniMessage.miniMessage();
		return miniMessage.deserialize(string, Placeholder.parsed("prefix",
				plugin.getConfigManager().getConfig().getString(ConfigKeys.PREFIX)));
	}
	
	public static List<Component> colorize(List<String> strings) {
		MiniMessage miniMessage = MiniMessage.miniMessage();
		return strings.stream().map(string -> miniMessage.deserialize(string,
				Placeholder.parsed("prefix", plugin.getConfigManager().getConfig().getString(ConfigKeys.PREFIX)))).collect(Collectors.toList());
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
					Bukkit.getConsoleSender().sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.SHELL_EXIT_WITH_CODE.replace("%code%", String.valueOf(exitCode)))));
				}else {
					commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.SHELL_EXIT_WITH_CODE.replace("%code%", String.valueOf(exitCode)))));
				}
			}
			
			if(outputToConsole) {
				commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.SHELL_SENT_CONSOLE_OUTPUT)));
			}
			
		} catch (IOException | InterruptedException e) {
			commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.SHELL_FAILED)));
			Bukkit.getLogger().warning(e.getMessage());
		}
	}
}
