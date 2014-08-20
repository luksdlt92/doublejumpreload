package com.github.luksdlt92.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.luksdlt92.DoubleJumpReload;
import com.github.luksdlt92.permissions.PermissionCanUseCommands;

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
			Player player = (Player) sender;
			
			if(player.hasPermission(new PermissionCanUseCommands().canUseCommands))
			{
		    	if (cmd.getName().equalsIgnoreCase("doublejumpdelay"))
		    	{
		    		player.sendMessage("La cantidad de PJs con cooldown son " + _plugin.getPlayers().size());
		    	}
		    	else if (cmd.getName().equalsIgnoreCase("doublejumpclear"))
		    	{
		    		synchronized(_plugin.getPlayers())
		    		{
		    			_plugin.getPlayers().clear();
		    			_plugin.getLogger().info("Se limpio la lista de cooldown de DoubleJump");
		    		}
		    	}
		    	return true;
			}
			else
			{
				player.sendMessage("No tenes permisos campeon");
				return false;
			}
		}
    	return false;
	}

}
