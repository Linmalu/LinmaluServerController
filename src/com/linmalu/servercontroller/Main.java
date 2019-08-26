package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluMain;
import org.bukkit.ChatColor;

public final class Main extends LinmaluMain
{
	public static final String SMART_MOVING_NAME = "SmartMoving".toLowerCase();
	public static final String SMART_MOVING_MESSAGE = ChatColor.YELLOW + "스마트무빙이 금지된 서버입니다.";

	private MainConfig _mainConfig;

	@Override
	public void onEnable()
	{
		super.onEnable();
		_mainConfig = new MainConfig();
		new MainCommand(this);
		new MainEvent(this);
	}

	public MainConfig GetMainConfig()
	{
		return _mainConfig;
	}
}
