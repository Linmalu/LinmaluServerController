package com.linmalu.servercontroller;

import com.linmalu.library.api.LinmaluConfig;

import java.io.File;

public class MainConfig
{
	private final LinmaluConfig _config = new LinmaluConfig(new File(Main.getInstance().getDataFolder(), "config.yml"));
	private final String CONFIG_FOOD_STOP = "FoodStop";
	private final String CONFIG_BLOCK_BREAK_STOP = "BlockBreakStop";
	private final String CONFIG_BLOCK_PLACE_STOP = "BlockPlaceStop";
	private final String CONFIG_CHATTING_STOP = "ChattingStop";
	private final String CONFIG_MOVE_STOP = "MoveStop";
	private final String CONFIG_ATTACK_STOP = "AttackStop";
	private final String CONFIG_SMART_MOVING_STOP = "SmartMovingStop";

	public MainConfig()
	{
		setFoodStop(isFoodStop());
		setBlockBreakStop(isBlockBreakStop());
		setBlockPlaceStop(isBlockPlaceStop());
		setChattingStop(isChattingStop());
		setMoveStop(isMoveStop());
		setAttackStop(isAttackStop());
		setSmartMovingStop(isSmartMovingStop());
		_config.save();
	}

	public boolean isFoodStop()
	{
		return _config.getBoolean(CONFIG_FOOD_STOP, false);
	}

	public void setFoodStop(boolean foodStop)
	{
		_config.set(CONFIG_FOOD_STOP, foodStop);
	}

	public boolean isBlockBreakStop()
	{
		return _config.getBoolean(CONFIG_BLOCK_BREAK_STOP, false);
	}

	public void setBlockBreakStop(boolean blockBreakStop)
	{
		_config.set(CONFIG_BLOCK_BREAK_STOP, blockBreakStop);
	}

	public boolean isBlockPlaceStop()
	{
		return _config.getBoolean(CONFIG_BLOCK_PLACE_STOP, false);
	}

	public void setBlockPlaceStop(boolean blockPlaceStop)
	{
		_config.set(CONFIG_BLOCK_PLACE_STOP, blockPlaceStop);
	}

	public boolean isChattingStop()
	{
		return _config.getBoolean(CONFIG_CHATTING_STOP, false);
	}

	public void setChattingStop(boolean chattingStop)
	{
		_config.set(CONFIG_CHATTING_STOP, chattingStop);
	}

	public boolean isMoveStop()
	{
		return _config.getBoolean(CONFIG_MOVE_STOP, false);
	}

	public void setMoveStop(boolean moveStop)
	{
		_config.set(CONFIG_MOVE_STOP, moveStop);
	}

	public boolean isAttackStop()
	{
		return _config.getBoolean(CONFIG_ATTACK_STOP, false);
	}

	public void setAttackStop(boolean attackStop)
	{
		_config.set(CONFIG_ATTACK_STOP, attackStop);
	}

	public boolean isSmartMovingStop()
	{
		return _config.getBoolean(CONFIG_SMART_MOVING_STOP, false);
	}

	public void setSmartMovingStop(boolean smartMovingStop)
	{
		_config.set(CONFIG_SMART_MOVING_STOP, smartMovingStop);
	}
}
