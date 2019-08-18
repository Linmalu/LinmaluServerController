package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluCommand;
import com.linmalu.library.api.LinmaluMain;
import com.linmalu.library.api.LinmaluTellraw;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class MainCommand extends LinmaluCommand
{
	public MainCommand(LinmaluMain main)
	{
		super(main);
	}

	@Override
	protected List<String> TabCompleter(CommandSender sender, Command command, String alias, String[] args)
	{
		return null;
	}

	private final String AllPlayerName = "전체";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		sender.sendMessage(ChatColor.GREEN + " = = = = = [ Linmalu ServerController ] = = = = =");
		LinmaluTellraw.sendChat(sender, "/" + label + " 추가 ", ChatColor.GOLD + "/" + label + " 추가" + ChatColor.GRAY + " : 단축키 추가");
		sender.sendMessage(ChatColor.YELLOW + "제작자 : " + ChatColor.AQUA + "린마루(Linmalu)" + ChatColor.WHITE + " - http://blog.linmalu.com");

		return true;
	}
}
