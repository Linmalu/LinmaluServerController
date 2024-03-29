package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

public class MainEvent extends LinmaluEvent
{
	private final MainConfig _config;

	public MainEvent(Main main)
	{
		super(main);
		_config = main.GetMainConfig();
	}

	@EventHandler
	public void event(FoodLevelChangeEvent event)
	{
		if(event.getEntityType() != EntityType.PLAYER)
		{
			return;
		}
		if(_config.isFoodStop())
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void event(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		if(player.isOp())
		{
			return;
		}
		if(_config.isBlockBreakStop())
		{
			event.setCancelled(true);
			player.sendMessage(_main.getTitle() + ChatColor.YELLOW + "금지된 행동입니다.");
		}
	}

	@EventHandler
	public void event(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		if(player.isOp())
		{
			return;
		}
		if(_config.isBlockPlaceStop())
		{
			event.setCancelled(true);
			player.sendMessage(_main.getTitle() + ChatColor.YELLOW + "금지된 행동입니다.");
		}
	}

	@EventHandler
	public void event(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		if(player.isOp())
		{
			return;
		}
		if(_config.isChattingStop())
		{
			event.setCancelled(true);
			player.sendMessage(_main.getTitle() + ChatColor.YELLOW + "금지된 행동입니다.");
		}
	}

	@EventHandler
	public void event(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		if(player.isOp())
		{
			return;
		}
		if(!_config.isMoveStop())
		{
			return;
		}
		Location fromLocation = event.getFrom();
		Location toLocation = event.getTo();
		if(fromLocation.getBlockX() == toLocation.getBlockX() && fromLocation.getBlockZ() == toLocation.getBlockZ())
		{
			return;
		}
		toLocation.setX(fromLocation.getBlockX() + 0.5);
		toLocation.setZ(fromLocation.getBlockZ() + 0.5);
		player.teleport(toLocation);
		player.sendMessage(_main.getTitle() + ChatColor.YELLOW + "금지된 행동입니다.");
	}

	@EventHandler
	public void event(EntityDamageByEntityEvent event)
	{
		Entity entity1 = event.getDamager();
		Entity entity2 = event.getEntity();
		if(!(entity1 instanceof Player && entity2 instanceof Player))
		{
			return;
		}
		Player player = (Player)entity1;
		if(player.isOp())
		{
			return;
		}
		if(_config.isAttackStop())
		{
			event.setCancelled(true);
			player.sendMessage(_main.getTitle() + ChatColor.YELLOW + "금지된 행동입니다.");
		}
	}

	@EventHandler
	public void event(PlayerRegisterChannelEvent event)
	{
		Player player = event.getPlayer();
		if(player.isOp())
		{
			return;
		}
		if(!_config.isSmartMovingStop())
		{
			return;
		}
		if(event.getChannel().toLowerCase().contains(Main.SMART_MOVING_NAME))
		{
			player.kickPlayer(_main.getTitle() + Main.SMART_MOVING_MESSAGE);
		}
	}
}
