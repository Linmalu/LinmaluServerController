package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluCommand;
import com.linmalu.library.api.LinmaluItemStack;
import com.linmalu.library.api.LinmaluPlayer;
import com.linmalu.library.api.LinmaluTellraw;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MainCommand extends LinmaluCommand
{
	private final String TAB_COMMAND = "명령어";
	private final String TAB_CHATTING = "채팅";
	private final String TAB_INVENTORY = "인벤";
	private final String TAB_CHEST = "상자";
	private final String TAB_ENDER_CHEST = "엔더상자";
	private final String TAB_ITEM = "아이템";
	private final String TAB_LEVEL = "레벨";
	private final String TAB_HEALTH = "체력";
	private final String TAB_FOOD = "배고픔";
	private final String TAB_PUSH = "밀기";
	private final String TAB_FOOD_STOP = "배고픔멈춤";
	private final String TAB_BLOCK_BREAK_STOP = "블럭파괴금지";
	private final String TAB_BLOCK_PLACE_STOP = "블럭설치금지";
	private final String TAB_CHATTING_STOP = "채팅금지";
	private final String TAB_MOVE_STOP = "이동금지";
	private final String TAB_ATTACK_STOP = "공격금지";
	private final String TAB_INFO = "정보";

	private final MainConfig _config;

	public MainCommand(Main main)
	{
		super(main);
		_config = main.GetMainConfig();
	}

	@Override
	protected List<String> TabCompleter(CommandSender sender, Command command, String alias, String[] args)
	{
		List<String> tabList = new ArrayList<>();
		if(!sender.isOp())
		{
			return tabList;
		}
		if(args.length == 1)
		{
			tabList.add(TAB_COMMAND);
			tabList.add(TAB_CHATTING);
			tabList.add(TAB_INVENTORY);
			tabList.add(TAB_CHEST);
			tabList.add(TAB_ENDER_CHEST);
			tabList.add(TAB_ITEM);
			tabList.add(TAB_LEVEL);
			tabList.add(TAB_HEALTH);
			tabList.add(TAB_PUSH);
			tabList.add(TAB_FOOD);
			tabList.add(TAB_BLOCK_BREAK_STOP);
			tabList.add(TAB_BLOCK_PLACE_STOP);
			tabList.add(TAB_FOOD_STOP);
			tabList.add(TAB_CHATTING_STOP);
			tabList.add(TAB_MOVE_STOP);
			tabList.add(TAB_ATTACK_STOP);
			tabList.add(TAB_INFO);
			return tabList;
		}
		return null;
	}

	private final String AllPlayerName = "전체";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!sender.isOp())
		{
			sender.sendMessage(_main.getTitle() + ChatColor.RED + "권한이 없습니다.");
			return true;
		}
		// 명령어
		if(args.length > 2 && args[0].equalsIgnoreCase(TAB_COMMAND))
		{
			String command = joinString(args, 2);
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				boolean op = player.isOp();
				if(!op)
				{
					player.setOp(true);
				}
				player.performCommand(command);
				if(!op)
				{
					player.setOp(false);
				}
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_COMMAND + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 채팅
		else if(args.length > 2 && args[0].equalsIgnoreCase(TAB_CHATTING))
		{
			String chat = joinString(args, 2);
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				player.chat(chat);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_CHATTING + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 인벤
		else if(args.length == 2 && args[0].equalsIgnoreCase(TAB_INVENTORY))
		{
			if(!(sender instanceof Player))
			{
				return true;
			}
			Player player = Bukkit.getPlayer(args[1]);
			if(player == null)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "플레이어가 접속중이 아닙니다.");
				return true;
			}
			((Player)sender).openInventory(player.getInventory());
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_INVENTORY + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 상자
		else if(args.length == 4 && args[0].equalsIgnoreCase(TAB_CHEST))
		{
			if(!(sender instanceof Player))
			{
				return true;
			}
			Player player = (Player)sender;
			int x, y, z;
			try
			{
				x = Integer.parseInt(args[1]);
				y = Integer.parseInt(args[2]);
				z = Integer.parseInt(args[3]);
			}
			catch(NumberFormatException ex)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "숫자가 입력되지 않았습니다.");
				return true;
			}
			BlockState blockState = player.getWorld().getBlockAt(x, y, z).getState();
			if(!(blockState instanceof Chest))
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "좌표에 상자가 없습니다.");
				return true;
			}
			player.openInventory(((Chest)blockState).getBlockInventory());
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_CHEST + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 엔더 상자
		else if(args.length == 2 && args[0].equalsIgnoreCase(TAB_ENDER_CHEST))
		{
			if(!(sender instanceof Player))
			{
				return true;
			}
			Player player = Bukkit.getPlayer(args[1]);
			if(player == null)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "플레이어가 접속중이 아닙니다.");
				return true;
			}
			((Player)sender).openInventory(player.getEnderChest());
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_ENDER_CHEST + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 아이템
		else if(args.length == 2 && args[0].equalsIgnoreCase(TAB_ITEM))
		{
			if(!(sender instanceof Player))
			{
				return true;
			}
			ItemStack item = ((Player)sender).getItemInHand();
			if(item == null || item.getType() == Material.AIR)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "아이템이 없습니다.");
				return true;
			}
			String msg = _main.getTitle() + ChatColor.AQUA + "[" + LinmaluItemStack.getName(item) + ChatColor.AQUA + "]" + ChatColor.GREEN + "아이템을 받았습니다.";
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				LinmaluPlayer.addItemStack(player, item);
				player.sendMessage(msg);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_ITEM + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 레벨
		else if(args.length == 3 && args[0].equalsIgnoreCase(TAB_LEVEL))
		{
			int level;
			try
			{
				level = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException ex)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "숫자가 입력되지 않았습니다.");
				return true;
			}
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				player.setLevel(level);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_LEVEL + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 체력
		else if(args.length == 3 && args[0].equalsIgnoreCase(TAB_HEALTH))
		{
			int health;
			try
			{
				health = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException ex)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "숫자가 입력되지 않았습니다.");
				return true;
			}
			if(health < 0)
			{
				health = 0;
			}
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				player.setHealth(player.getMaxHealth() < health ? player.getMaxHealth() : health);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_HEALTH + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 배고픔
		else if(args.length == 3 && args[0].equalsIgnoreCase(TAB_FOOD))
		{
			int food;
			try
			{
				food = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException ex)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "숫자가 입력되지 않았습니다.");
				return true;
			}
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				player.setFoodLevel(food);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_FOOD + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 밀기
		else if(args.length == 3 && args[0].equalsIgnoreCase(TAB_PUSH))
		{
			if(!(sender instanceof Player))
			{
				return true;
			}
			Vector vector = ((Player)sender).getLocation().getDirection().normalize();
			try
			{
				vector = vector.multiply(Float.parseFloat(args[2]));
			}
			catch(NumberFormatException ex)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "숫자가 입력되지 않았습니다.");
				return true;
			}
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				player.setVelocity(vector);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_PUSH + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		else if(args.length == 5 && args[0].equalsIgnoreCase(TAB_PUSH))
		{
			Vector vector;
			try
			{
				vector = new Vector(Float.parseFloat(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]));
			}
			catch(NumberFormatException ex)
			{
				sender.sendMessage(_main.getTitle() + ChatColor.YELLOW + "숫자가 입력되지 않았습니다.");
				return true;
			}
			for(Player player : LinmaluPlayer.getPlayers(sender, args[1]))
			{
				player.setVelocity(vector);
			}
			sender.sendMessage(_main.getTitle() + ChatColor.GOLD + TAB_PUSH + ChatColor.GREEN + " 기능을 사용했습니다.");
			return true;
		}
		// 배고픔 금지
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_FOOD_STOP))
		{
			if(_config.isFoodStop())
			{
				_config.setFoodStop(false);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_BLOCK_BREAK_STOP + ChatColor.GREEN + " 기능이 취소되었습니다.");
			}
			else
			{
				_config.setFoodStop(true);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_BLOCK_BREAK_STOP + ChatColor.GREEN + " 기능이 작동되었습니다.");
			}
			return true;
		}
		// 블럭 파괴 금지
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_BLOCK_BREAK_STOP))
		{
			if(_config.isBlockBreakStop())
			{
				_config.setBlockBreakStop(false);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_BLOCK_BREAK_STOP + ChatColor.GREEN + " 기능이 취소되었습니다.");
			}
			else
			{
				_config.setBlockBreakStop(true);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_BLOCK_BREAK_STOP + ChatColor.GREEN + " 기능이 작동되었습니다.");
			}
			return true;
		}
		// 블럭 설치 금지
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_BLOCK_PLACE_STOP))
		{
			if(_config.isBlockPlaceStop())
			{
				_config.setBlockPlaceStop(false);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_BLOCK_PLACE_STOP + ChatColor.GREEN + " 기능이 취소되었습니다.");
			}
			else
			{
				_config.setBlockPlaceStop(true);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_BLOCK_PLACE_STOP + ChatColor.GREEN + " 기능이 작동되었습니다.");
			}
			return true;
		}
		// 채팅 금지
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_CHATTING_STOP))
		{
			if(_config.isChattingStop())
			{
				_config.setChattingStop(false);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_CHATTING_STOP + ChatColor.GREEN + " 기능이 취소되었습니다.");
			}
			else
			{
				_config.setChattingStop(true);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_CHATTING_STOP + ChatColor.GREEN + " 기능이 작동되었습니다.");
			}
			return true;
		}
		// 이동 금지
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_MOVE_STOP))
		{
			if(_config.isMoveStop())
			{
				_config.setMoveStop(false);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_MOVE_STOP + ChatColor.GREEN + " 기능이 취소되었습니다.");
			}
			else
			{
				_config.setMoveStop(true);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_MOVE_STOP + ChatColor.GREEN + " 기능이 작동되었습니다.");
			}
			return true;
		}
		// 공격 금지
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_ATTACK_STOP))
		{
			if(_config.isAttackStop())
			{
				_config.setAttackStop(false);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_ATTACK_STOP + ChatColor.GREEN + " 기능이 취소되었습니다.");
			}
			else
			{
				_config.setAttackStop(true);
				Bukkit.broadcastMessage(_main.getTitle() + ChatColor.GOLD + TAB_ATTACK_STOP + ChatColor.GREEN + " 기능이 작동되었습니다.");
			}
			return true;
		}
		// 정보
		else if(args.length == 1 && args[0].equalsIgnoreCase(TAB_INFO))
		{
			sender.sendMessage(ChatColor.GREEN + " = = = = = [ Linmalu ServerController ] = = = = =");
			sender.sendMessage(ChatColor.GOLD + TAB_FOOD_STOP + ChatColor.WHITE + " : " + (_config.isFoodStop() ? ChatColor.GREEN + "작동" : ChatColor.YELLOW + "취소"));
			sender.sendMessage(ChatColor.GOLD + TAB_BLOCK_BREAK_STOP + ChatColor.WHITE + " : " + (_config.isBlockBreakStop() ? ChatColor.GREEN + "작동" : ChatColor.YELLOW + "취소"));
			sender.sendMessage(ChatColor.GOLD + TAB_BLOCK_PLACE_STOP + ChatColor.WHITE + " : " + (_config.isBlockPlaceStop() ? ChatColor.GREEN + "작동" : ChatColor.YELLOW + "취소"));
			sender.sendMessage(ChatColor.GOLD + TAB_CHATTING_STOP + ChatColor.WHITE + " : " + (_config.isChattingStop() ? ChatColor.GREEN + "작동" : ChatColor.YELLOW + "취소"));
			sender.sendMessage(ChatColor.GOLD + TAB_MOVE_STOP + ChatColor.WHITE + " : " + (_config.isMoveStop() ? ChatColor.GREEN + "작동" : ChatColor.YELLOW + "취소"));
			sender.sendMessage(ChatColor.GOLD + TAB_ATTACK_STOP + ChatColor.WHITE + " : " + (_config.isAttackStop() ? ChatColor.GREEN + "작동" : ChatColor.YELLOW + "취소"));
			return true;
		}
		sender.sendMessage(ChatColor.GREEN + " = = = = = [ Linmalu ServerController ] = = = = =");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_COMMAND, ChatColor.GOLD + "/" + label + " " + TAB_COMMAND + " <플레이어> <명령어> ..." + ChatColor.GRAY + " : 플레이어에게 OP권한으로 명령어를 대신 사용합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_CHATTING, ChatColor.GOLD + "/" + label + " " + TAB_CHATTING + " <플레이어> <채팅> ..." + ChatColor.GRAY + " : 플레이어에게 채팅을 대신 사용합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_INVENTORY, ChatColor.GOLD + "/" + label + " " + TAB_INVENTORY + " <플레이어>" + ChatColor.GRAY + " : 플레이어의 인벤을 엽니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_CHEST, ChatColor.GOLD + "/" + label + " " + TAB_CHEST + " <X> <Y> <Z>" + ChatColor.GRAY + " : 좌표에 있는 상자를 엽니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_ENDER_CHEST, ChatColor.GOLD + "/" + label + " " + TAB_ENDER_CHEST + " <플레이어>" + ChatColor.GRAY + " : 플레이어의 엔더상자를 엽니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_ITEM, ChatColor.GOLD + "/" + label + " " + TAB_ITEM + " <플레이어>" + ChatColor.GRAY + " : 아이템을 플레이어에게 줍니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_LEVEL, ChatColor.GOLD + "/" + label + " " + TAB_LEVEL + " <플레이어> <레벨>" + ChatColor.GRAY + " : 플레이어의 레벨을 설정합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_HEALTH, ChatColor.GOLD + "/" + label + " " + TAB_HEALTH + " <플레이어> <체력>" + ChatColor.GRAY + " : 플레이어의 체력을 설정합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_FOOD, ChatColor.GOLD + "/" + label + " " + TAB_FOOD + " <플레이어> <배고픔>" + ChatColor.GRAY + " : 플레이어의 배고픔을 설정합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_PUSH, ChatColor.GOLD + "/" + label + " " + TAB_PUSH + " <플레이어> <힘>" + ChatColor.GRAY + " : 플레이어를 자신의 보는 방향으로 밉니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_PUSH, ChatColor.GOLD + "/" + label + " " + TAB_PUSH + " <플레이어> <X> <Y> <Z>" + ChatColor.GRAY + " : 플레이어를 좌표방향으로 밉니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_FOOD_STOP, ChatColor.GOLD + "/" + label + " " + TAB_FOOD_STOP + ChatColor.GRAY + " : 배고픔을 멈춥니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_BLOCK_BREAK_STOP, ChatColor.GOLD + "/" + label + " " + TAB_BLOCK_BREAK_STOP + ChatColor.GRAY + " : 블럭파괴를 금지합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_BLOCK_PLACE_STOP, ChatColor.GOLD + "/" + label + " " + TAB_BLOCK_PLACE_STOP + ChatColor.GRAY + " : 블럭설치를 금지합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_CHATTING_STOP, ChatColor.GOLD + "/" + label + " " + TAB_CHATTING_STOP + ChatColor.GRAY + " : 채팅을 금지합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_MOVE_STOP, ChatColor.GOLD + "/" + label + " " + TAB_MOVE_STOP + ChatColor.GRAY + " : 이동을 금지합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_ATTACK_STOP, ChatColor.GOLD + "/" + label + " " + TAB_ATTACK_STOP + ChatColor.GRAY + " : 공격을 금지합니다.");
		LinmaluTellraw.sendChat(sender, "/" + label + " " + TAB_INFO, ChatColor.GOLD + "/" + label + " " + TAB_INFO + ChatColor.GRAY + " : 설정된 정보를 확인합니다.");
		sender.sendMessage(ChatColor.YELLOW + "제작자 : " + ChatColor.AQUA + "린마루(Linmalu)" + ChatColor.WHITE + " - http://blog.linmalu.com");
		return true;
	}
}
