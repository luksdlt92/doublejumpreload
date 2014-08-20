package com.github.luksdlt92.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitTask;

import com.github.luksdlt92.DoubleJumpReload;
import com.github.luksdlt92.task.JumpAgain;
import com.github.luksdlt92.utils.Utils;

public class JumpListener implements Listener {
	
	private final DoubleJumpReload _plugin;
	
	public JumpListener(DoubleJumpReload plugin)
	{
		_plugin = plugin;
		_plugin.getServer().getPluginManager().registerEvents(this, _plugin);
	}
       
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) 
    {
    	Player player = event.getPlayer();
    	
    	if (!Utils.isInCreative(player) && !player.isFlying() && !_plugin.getPlayersDisableJump().contains(player.getName()))
    	{
    		if (Utils.isInAir(player))
    		{
        		if (!_plugin.getPlayers().contains(player.getName()))
        		{
        			_plugin.getPlayers().add(player.getName());
        		}
    		}
    		else
    		{
        		if (_plugin.getPlayers().contains(player.getName()))
        		{
        			_plugin.getPlayers().remove(player.getName());
        		}
    		}
    	}
    }
    
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event)
    {
    	Player player = event.getPlayer();
    	
    	if (Utils.isInCreative(player))
    		return;
    	
		if (Utils.isInAir(player) && _plugin.getPlayers().contains(player.getName()))
		{
			if (Utils.hasOnePossibleDirection(player))
			{
				player.setVelocity(player.getLocation().getDirection().multiply(-0.4).setY(0.8));
				player.sendMessage("Double Jump contra la pared!");
			}
			else
			{
				player.setVelocity(player.getLocation().getDirection().multiply(0.4).setY(0.5));
				player.sendMessage("Double Jump!");
			}
			
			_plugin.getPlayers().remove(player.getName());
			
			if (!_plugin.getPlayersDisableJump().contains(player.getName()))
			{
				_plugin.getPlayersDisableJump().add(player.getName());
			}
			
			@SuppressWarnings("unused")
			BukkitTask task = new JumpAgain(_plugin, player.getName()).runTaskLater(_plugin, 100);
		}
    }
}
