package com.doko.jonas.dataCollect;

import java.util.ArrayList;
import java.util.List;

import com.doko.jonas.dataTypes.Player;

public class PlayerPool
{

	private static final List<Player> players = new ArrayList<>();

	public static Player getOrCreatePlayer(String name)
	{
		return players.stream().filter(p -> p.getName().equals(name)).findFirst().orElseGet(() -> createPlayer(name));
	}

	private static Player createPlayer(String name)
	{
		Player player = new Player(name);
		players.add(player);
		return player;
	}

	public static List<Player> getAllPlayers()
	{
		return players;
	}

}
