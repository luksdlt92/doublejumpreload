package com.minekkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitTask;

import com.minekkit.DoubleJumpReload;
import com.minekkit.task.JumpAgain;
import com.minekkit.utils.Utils;

public class JumpListener implements Listener {
	
	private final DoubleJumpReload _plugin;
	
	public JumpListener(DoubleJumpReload plugin)
	{
		_plugin = plugin;
		_plugin.getServer().getPluginManager().registerEvents(this, _plugin);
	}
	
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) 
    {
    	Player player = event.getPlayer();
    	if(Utils.isInCreative(player))
    		return;
    	
    	event.setCancelled(true);
    	player.setAllowFlight(false);
    	player.setFlying(false);
    	
    	//if (Utils.isInAllowWorld(player))
    	//{
    		player.setVelocity(player.getLocation().getDirection().multiply(0.2).setY(0.5));
    	//}
    	
    	synchronized(_plugin.getPlayers())
    	{
        	if (_plugin.getPlayers().isEmpty() || !_plugin.getPlayers().contains(player.getName()))
        	{
        		_plugin.getPlayers().add(player.getName());
        	}
    	}
    	
    	@SuppressWarnings("unused")
		BukkitTask task = new JumpAgain(_plugin, player.getName()).runTaskLater(_plugin, 100);
    	_plugin.getLogger().info("Libertaaaaad (salto)");
    }
       
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) 
    {
    	Player player = event.getPlayer();
    	
    	//if (Utils.isInAllowWorld(player))
    	//{
        	if(!Utils.isInCreative(player) && !Utils.isInAir(player) &&(!player.isFlying())) 
        	{
        		synchronized(_plugin.getPlayers())
        		{
            		if (!_plugin.getPlayers().contains(player.getName()))
            		{
            			player.setAllowFlight(true);
            		}
        		}
        	}
    	//}
    }
	
}
