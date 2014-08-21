package com.github.luksdlt92.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitTask;

import com.github.luksdlt92.DoubleJumpReload;
import com.github.luksdlt92.permissions.PermissionCanJump;
import com.github.luksdlt92.task.JumpAgain;
import com.github.luksdlt92.utils.Utils;

public class JumpListener implements Listener {
	
	private static final int MIN_FOOD_LEVEL = 6;
	private static final int TICKS_DELAY = 50; // If the server is not lagged, 20 ticks = 1 second
	private final DoubleJumpReload _plugin;
	
	public JumpListener(DoubleJumpReload plugin)
	{
		_plugin = plugin;
		_plugin.getServer().getPluginManager().registerEvents(this, _plugin);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		if (_plugin.getPlayers().contains(player.getName()))
		{
			_plugin.getPlayers().remove(player.getName());
		}
		
		if (_plugin.getPlayersEnabled().contains(player.getName()))
		{
			_plugin.getPlayersEnabled().remove(player.getName());
		}
	}
    
	@EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event)
    {
    	Player player = event.getPlayer();
    	
    	if(player.hasPermission(new PermissionCanJump().canJump)) 
    	{
        	if (Utils.isInCreative(player))
        		return;
        	
        	if (_plugin.getPlayersEnabled().contains(player.getName()))
        	{
        		if (player.getFoodLevel() > MIN_FOOD_LEVEL && !_plugin.getPlayers().contains(player.getName()))
            	{
            			if (Utils.hasOnePossibleDirection(player))
            			{
            				player.setVelocity(player.getLocation().getDirection().multiply(-0.4).setY(1));
            				player.sendMessage(ChatColor.GREEN + "[DoubleJumpReload]" + ChatColor.WHITE + " Double Jump contra la pared!");
            			}
            			else
            			{
            				if (Utils.isInAir(player))
            				{
            					player.setVelocity(player.getLocation().getDirection().multiply(0.4).setY(1.05));
            				}
            				else
            				{
            					player.setVelocity(player.getLocation().getDirection().multiply(0.4).setY(0.9));
            				}
                			
                			player.sendMessage(ChatColor.GREEN + "[DoubleJumpReload]" + ChatColor.WHITE + " Double Jump!");
            			}
            			
            			@SuppressWarnings("unused")
            			BukkitTask task = new JumpAgain(_plugin, player).runTaskLater(_plugin, TICKS_DELAY);
            			
        				_plugin.getPlayers().add(player.getName());
            	}
        	}
    	}
    }
}
