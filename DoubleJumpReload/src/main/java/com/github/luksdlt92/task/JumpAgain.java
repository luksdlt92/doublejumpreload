package com.github.luksdlt92.task;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.luksdlt92.DoubleJumpReload;

/*
 * Task que se ejecuta luego de un salto
 * Vendria a ser un antifeed
 */

public class JumpAgain extends BukkitRunnable {

	private final DoubleJumpReload _plugin;
	private final Player _player;
	
	public JumpAgain (DoubleJumpReload plugin, Player player)
	{
		_plugin = plugin;
		_player = player;
	}
	
	@Override
	public void run() {
		synchronized(_plugin.getPlayers())
		{
			if (_plugin.getPlayers().contains(_player.getName()))
			{
				_plugin.getPlayers().remove(_player.getName());
				_player.sendMessage(ChatColor.BLUE + "[DoubleJumpReload]" + ChatColor.WHITE + " Ya podes saltar de nuevo!");
			}
		}
	}
	
}
