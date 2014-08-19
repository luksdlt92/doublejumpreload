package com.minekkit;
 
import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.minekkit.commands.DoubleJumpCommand;
import com.minekkit.listeners.JumpListener;
 
public class DoubleJumpReload extends JavaPlugin implements Listener {

	public ArrayList<String> _players = new ArrayList<String>();
	
    @Override
    public void onEnable()
    {	
    	new JumpListener(this);
    	this.getCommand("jumpdelay").setExecutor(new DoubleJumpCommand(this));
    }
    
    public ArrayList<String> getPlayers()
    {
    	return _players;
    }
}
