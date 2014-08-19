package com.minekkit.utils;

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

}
