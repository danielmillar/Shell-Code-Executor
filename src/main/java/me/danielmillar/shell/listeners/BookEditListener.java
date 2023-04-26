package me.danielmillar.shell.listeners;

import me.danielmillar.shell.Shell;
import me.danielmillar.shell.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookEditListener implements Listener {
	
	private Shell plugin;
	
	public BookEditListener(Shell plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBookEdit(PlayerEditBookEvent event) {
		BookMeta meta = event.getNewBookMeta();
		
		NamespacedKey key = new NamespacedKey(plugin, "shell_book");
		if(!meta.getPersistentDataContainer().has(key)) return;
		
		Player player = event.getPlayer();
		if(!player.hasPermission("shell.execute")) return;
		
		List<Component> commands = meta.pages();
		StringBuilder commandBuilder = new StringBuilder();
		for (Component command : commands) {
			String commandStr = LegacyComponentSerializer.legacySection().serialize(command).trim();
			commandBuilder.append(commandStr).append(" ");
		}
		
		String combinedCommandStr = commandBuilder.toString().trim();
		if(combinedCommandStr.isEmpty()) return;
		
		String[] commandParts = combinedCommandStr.split(" ");
		List<String> commandList = new ArrayList<>(Arrays.asList(commandParts));
		
		Utils.executeShell(player, commandList, false);
	}
	
}
