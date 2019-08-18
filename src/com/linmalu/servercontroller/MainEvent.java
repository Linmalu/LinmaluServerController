package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluEvent;
import com.linmalu.library.api.LinmaluMain;

public class MainEvent extends LinmaluEvent
{
	public MainEvent(LinmaluMain main)
	{
		super(main);
	}
}
