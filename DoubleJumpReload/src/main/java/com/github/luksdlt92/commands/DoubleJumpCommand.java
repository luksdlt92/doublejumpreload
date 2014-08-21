package com.github.luksdlt92.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.luksdlt92.DoubleJumpReload;
import com.github.luksdlt92.permissions.PermissionCanUseCommands;
import com.github.luksdlt92.permissions.PermissionToggleJump;

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
			
			if(player.hasPermission(new PermissionToggleJump().toggleJump))
			{
		    	if (cmd.getName().equalsIgnoreCase("djtoggle"))
		    	{
		    		if (_plugin.getPlayersEnabled().contains(player.getName()))
		    		{
		    			_plugin.getPlayersEnabled().remove(player.getName());
		    			player.sendMessage(ChatColor.GREEN + "[DoubleJumpReload]" + ChatColor.WHITE + " Se ha desactivado");
		    		}
		    		else
		    		{
		    			_plugin.getPlayersEnabled().add(player.getName());
		    			player.sendMessage(ChatColor.GREEN + "[DoubleJumpReload]" + ChatColor.WHITE + " Se ha activado");
		    		}
		    		return true;
		    	}
			}
			
			if(player.hasPermission(new PermissionCanUseCommands().canUseCommands))
			{
		    	if (cmd.getName().equalsIgnoreCase("djdelay"))
		    	{
		    		player.sendMessage("La cantidad de PJs con cooldown son " + _plugin.getPlayers().size());
		    	}
		    	else if (cmd.getName().equalsIgnoreCase("djclear"))
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
				player.sendMessage("No tenes permisos master");
				return false;
			}
		}
    	return false;
	}

}
