package me.danielmillar.shell.listeners;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabCompleteListener implements Listener {
	
	@EventHandler
	public void onAsyncTabComplete(AsyncTabCompleteEvent event) {
		if (!event.isCommand() || !event.getBuffer().contains(" ")) return;
		
		String[] splitBuffer = event.getBuffer().substring(1).split(" +", -1);
		String commandAlias = splitBuffer[0];
		String[] args = Arrays.copyOfRange(splitBuffer, 1, splitBuffer.length);

		if(commandAlias.equalsIgnoreCase("shell")){
			if(args.length == 1){
				List<String> arguments = new ArrayList<>();
				arguments.add("-u");
				arguments.add("-c");
				
				event.setCompletions(arguments);
				return;
			}
			
			event.setCompletions(Collections.singletonList(""));
		}
	}
}
