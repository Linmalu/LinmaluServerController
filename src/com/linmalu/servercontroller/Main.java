package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluMain;

public final class Main extends LinmaluMain
{
	public static Main getInstance()
	{
		return (Main)LinmaluMain.getInstance();
	}

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