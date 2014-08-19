package com.github.luksdlt92.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.luksdlt92.DoubleJumpReload;

public class DoubleJumpCommand implements CommandExecutor {
	
	private final DoubleJumpReload _plugin;
	
	public DoubleJumpCommand (DoubleJumpReload plugin)
	{
		_plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if (sender instanceof Player)
		{
	    	if (cmd.getName().equalsIgnoreCase("jumpdelay"))
	    	{
	    		synchronized (_plugin.getPlayers())
	    		{
	        		for (String p : _plugin.getPlayers())
	        		{
	        			_plugin.getLogger().info("Player con delay -> " + p);
	        		}
	    		}

	    		return true;
	    	}
		}
    	return false;
	}

}
