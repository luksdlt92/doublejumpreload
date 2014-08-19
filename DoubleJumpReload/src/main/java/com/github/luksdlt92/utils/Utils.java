package com.github.luksdlt92.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Utils {
	
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
	
	public static int distanceToFloor(Player player, int limit)
	{
		int i = 0;
		
		for (int count = 0; count < limit; count++)
		{
			if (player.getLocation().subtract(0, i, 0).getBlock().getType() == Material.AIR)
			{
				i++;
			}
			else
			{
				break;
			}
		}
		return i;
	}

}
