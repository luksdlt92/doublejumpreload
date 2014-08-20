package com.github.luksdlt92.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Utils {
	
	private static Material[] MATERIALS =
	{
		Material.AIR,
		Material.YELLOW_FLOWER,
		Material.RED_ROSE,
		Material.RED_MUSHROOM,
		Material.BROWN_MUSHROOM
	};
	
	public static boolean isInCreative(Player player)
	{
		if (player.getGameMode() == GameMode.CREATIVE)
		{
			return true;
		}
		return false;
	}
	
	public static boolean isInAir(Player player)
	{
		if(player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.AIR)
		{
			return true;
		}
		return false;
	}
	
	public static boolean isTraversableBlock(Player player)
	{
		for (Material m : MATERIALS)
		{
			if(player.getLocation().subtract(0, 1, 0).getBlock().getType() == m)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasOnePossibleDirection(Player player)
	{
    	int i = 0;
    	
    	if (player.getLocation().subtract(1, 0, 0).getBlock().getType() != Material.AIR)
    	{
    		i++;
    	}
    	
    	if (player.getLocation().subtract(-1, 0, 0).getBlock().getType() != Material.AIR)
    	{
    		i++;
    	}
    	
    	if (player.getLocation().subtract(0, 0, 1).getBlock().getType() != Material.AIR)
    	{
    		i++;
    	}
    	
    	if (player.getLocation().subtract(0, 0, -1).getBlock().getType() != Material.AIR)
    	{
    		i++;
    	}
    	
    	if (i == 1)
    	{
    		return true;
    	}
    	return false;
	}
}
