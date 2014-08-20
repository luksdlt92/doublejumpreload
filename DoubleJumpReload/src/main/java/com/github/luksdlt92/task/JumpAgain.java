package com.github.luksdlt92.task;

import org.bukkit.scheduler.BukkitRunnable;

import com.github.luksdlt92.DoubleJumpReload;

/*
 * Task que se ejecuta luego de un salto
 * Vendria a ser un antifeed
 */

public class JumpAgain extends BukkitRunnable {

	private final DoubleJumpReload _plugin;
	private final String _playername;
	
	public JumpAgain (DoubleJumpReload plugin, String playerName)
	{
		_plugin = plugin;
		_playername = playerName;
	}
	
	@Override
	public void run() {
		synchronized(_plugin.getPlayersDisableJump())
		{
			if (_plugin.getPlayersDisableJump().contains(_playername))
			{
				_plugin.getPlayersDisableJump().remove(_playername);
			}
		}
	}
	
}
