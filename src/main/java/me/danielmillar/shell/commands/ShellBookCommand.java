package me.danielmillar.shell.commands;

import me.danielmillar.shell.Shell;
import me.danielmillar.shell.utils.ConfigKeys;
import me.danielmillar.shell.utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShellBookCommand implements CommandExecutor {
	
	private Shell plugin;
	
	public ShellBookCommand(Shell plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (!commandSender.hasPermission("shell.execute")) {
			commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.NO_PERMISSION)));
			return true;
		}
		
		if(!(commandSender instanceof Player)){
			commandSender.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.ONLY_PLAYERS)));
			return true;
		}
		
		ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
		item.editMeta(meta -> {
			meta.displayName(Utils.colorize(plugin.getConfigManager().getConfig().getString("shellBook.name")).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
			List<Component> lore = Utils.colorize(plugin.getConfigManager().getConfig().getStringList("shellBook.lore"));
			for (int i = 0; i < lore.size(); i++) {
				lore.set(i, lore.get(i).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
			}
			meta.lore(lore);
			
			NamespacedKey key = new NamespacedKey(plugin, "shell_book");
			meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
		});
		
		Player player = (Player) commandSender;
		player.getInventory().addItem(item);
		player.sendMessage(Utils.colorize(plugin.getConfigManager().getConfig().getString(ConfigKeys.SHELL_BOOK_GIVEN)));
		return true;
	}
	
}
