package com.doko.jonas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.doko.jonas.dataCollect.DataCollector;
import com.doko.jonas.dataTypes.Game;
import com.doko.jonas.dataTypes.Player;
import com.doko.jonas.dataTypes.Session;
import com.doko.jonas.fileRead.FileMapper;
import com.vaadin.cdi.annotation.VaadinSessionScoped;

@VaadinSessionScoped
public class DataService
{
	private DataCollector dataCollector;
	
	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		FileMapper mapper = new FileMapper();
		List<Session> sessions = mapper.calculateSessions("/data");
		dataCollector = new DataCollector(sessions);
	}

	public String getWonPercentagePerPlayer(Player player)
	{
		return dataCollector.getWonPercentagePerPlayer(player);
	}

	public String getWonPercentagePerPlayerWhenStart(Player player)
	{
		return dataCollector.getWonPercentagePerPlayerWhenStart(player);
	}
 
	public String getSoloWonPercentagePerPlayer(Player player)
	{
		return dataCollector.getSoloWonPercentagePerPlayer(player);
	}

	public String getAverageScorePerGame()
	{
		return dataCollector.getAverageScorePerGame();
	}

	public Map<LocalDate, String> getAverageScorePerSession()
	{
		return dataCollector.getAverageScorePerSession();
	}

	public List<Game> getSolosForPlayer(Player player)
	{
		return dataCollector.getSolosForPlayer(player);
	}

	public List<Game> getGamesDealtByPlayer(Player player)
	{
		return dataCollector.getGamesDealtByPlayer(player);
	}

	public int getGameCount()
	{
		return dataCollector.getGameCount();
	}

	public int getSoloCount()
	{
		return dataCollector.getSoloCount();
	}

	public double getOverallMoneyPaid()
	{
		return dataCollector.getOverallMoneyPaid();
	}
	
	public List<Game> getAllGames()
	{
		return dataCollector.getAllGames();
	}
}
