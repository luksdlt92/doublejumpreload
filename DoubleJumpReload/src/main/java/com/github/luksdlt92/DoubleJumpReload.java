package com.github.luksdlt92;
 
import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.luksdlt92.commands.DoubleJumpCommand;
import com.github.luksdlt92.listeners.JumpListener;
import com.github.luksdlt92.permissions.PermissionCanJump;
import com.github.luksdlt92.permissions.PermissionCanUseCommands;
import com.github.luksdlt92.permissions.PermissionToggleJump;

/*
 * DoubleJumpReload plugin
 * Made by luksdlt92 and Abdalion
 */
 
public class DoubleJumpReload extends JavaPlugin implements Listener {

	public Permission playerPermission1 = new Permission("DoubleJumpReload.Jump");
	public Permission playerPermission2 = new Permission("DoubleJumpReload.JumpCommands");
	public Permission playerPermission3 = new Permission("DoubleJumpReload.ToggleJump");
	
	public ArrayList<String> _players = new ArrayList<String>();
	public ArrayList<String> _playersEnabled = new ArrayList<String>();
	
    @Override
    public void onEnable()
    {	
    	PluginManager pm = getServer().getPluginManager();
    	pm.addPermission(new PermissionCanJump().canJump);
    	pm.addPermission(new PermissionCanUseCommands().canUseCommands);
    	pm.addPermission(new PermissionToggleJump().toggleJump);
    	
    	new JumpListener(this);
    	this.getCommand("djdelay").setExecutor(new DoubleJumpCommand(this));
    	this.getCommand("djclear").setExecutor(new DoubleJumpCommand(this));
    	this.getCommand("djtoggle").setExecutor(new DoubleJumpCommand(this));
    }
    
    @Override
    public void onDisable()
    {
    	_players.clear();
    }
    
    public ArrayList<String> getPlayers()
    {
    	return _players;
    }
    
    public ArrayList<String> getPlayersEnabled()
    {
    	return _playersEnabled;
    }
    
}
