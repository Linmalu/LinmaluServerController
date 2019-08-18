package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluMain;

public final class Main extends LinmaluMain
{
	public static Main getInstance()
	{
		return (Main)LinmaluMain.getInstance();
	}

	@Override
	public void onEnable()
	{
		super.onEnable();
		new MainCommand(this);
		new MainEvent(this);
	}
}
