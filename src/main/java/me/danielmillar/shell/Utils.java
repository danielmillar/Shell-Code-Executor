package me.danielmillar.shell;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Utils {
	
	public static Component colorize(String string) {
		MiniMessage miniMessage = MiniMessage.miniMessage();
		return miniMessage.deserialize(string);
	}
	
}
