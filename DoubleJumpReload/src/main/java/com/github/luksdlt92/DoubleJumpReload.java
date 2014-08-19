package com.github.luksdlt92;
 
import java.util.ArrayList;

/*
 * DoubleJumpReload plugin
 * Made by luksdlt92 and Abdalion
 */

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.luksdlt92.commands.DoubleJumpCommand;
import com.github.luksdlt92.listeners.JumpListener;
 
public class DoubleJumpReload extends JavaPlugin implements Listener {

	public ArrayList<String> _players = new ArrayList<String>();
	public ArrayList<String> _playersDisableJump = new ArrayList<String>();
	
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
    
    public ArrayList<String> getPlayersDisableJump()
    {
    	return _playersDisableJump;
    }
}
