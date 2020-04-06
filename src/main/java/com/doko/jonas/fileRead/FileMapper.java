package com.doko.jonas.fileRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.doko.jonas.dataCollect.PlayerPool;
import com.doko.jonas.dataTypes.Game;
import com.doko.jonas.dataTypes.Player;
import com.doko.jonas.dataTypes.PlayerScore;
import com.doko.jonas.dataTypes.Position;
import com.doko.jonas.dataTypes.Session;
import com.doko.jonas.exceptions.InvalidDealerException;
import com.doko.jonas.exceptions.InvalidHeaderException;

public class FileMapper
{

	public List<Session> calculateSessions(String folderString) throws IOException, URISyntaxException
	{
		List<File> files = loadFiles(folderString);

		List<Session> sessions = new ArrayList<>();

		for (File file : files)
		{
			Session session = transformFileToSession(file);
			sessions.add(session);
		}

		return sessions;
	}

	private Session transformFileToSession(File file) throws IOException
	{
		BufferedReader csvReader = new BufferedReader(new FileReader(file));

		LocalDate date = mapFileNameToDate(file.getName());
		Session session = new Session(date);

		String firstline = csvReader.readLine();
		String[] firstLineArray = firstline.split(",");
		checkFirstLineArray(firstLineArray);
		Player player1 = PlayerPool.getOrCreatePlayer(firstLineArray[1]);
		Player player2 = PlayerPool.getOrCreatePlayer(firstLineArray[2]);
		Player player3 = PlayerPool.getOrCreatePlayer(firstLineArray[3]);
		Player player4 = PlayerPool.getOrCreatePlayer(firstLineArray[4]);

		List<Game> gamesOfSession = extractGames(csvReader, player1, player2, player3, player4, date);
		session.setGames(gamesOfSession);
		return session;

	}

	private void checkFirstLineArray(String[] firstLineArray) throws InvalidHeaderException
	{
		if (firstLineArray[0].equals("Dealer") && firstLineArray[5].equals("Solo"))
		{
			return;
		}
		throw new InvalidHeaderException("Dealer and Solo should be part of the csv header!");
	}

	private List<Game> extractGames(BufferedReader csvReader, Player player1, Player player2, Player player3,
			Player player4, LocalDate date) throws IOException
	{
		List<Game> gamesOfSession = new ArrayList<>();
		String line;
		while ((line = csvReader.readLine()) != null)
		{
			String[] gameData = line.split(",");

			String possibleSoloPlayer = calcSoloPlayer(gameData);

			List<PlayerScore> playerScores = calcPlayerScores(gameData, player1, player2, player3, player4);

			gamesOfSession.add(new Game(playerScores, calcDealer(gameData[0], player1, player2, player3, player4),
					mapPlayerByName(possibleSoloPlayer, player1, player2, player3, player4), date));

		}
		return gamesOfSession;
	}

	private List<PlayerScore> calcPlayerScores(String[] gameData, Player player1, Player player2, Player player3,
			Player player4) throws InvalidDealerException
	{
		List<PlayerScore> playerScores = new ArrayList<PlayerScore>();

		Player dealer = calcDealer(gameData[0], player1, player2, player3, player4);

		Player soloPlayer = mapPlayerByName(calcSoloPlayer(gameData), player1, player2, player3, player4);

		//calculate positions
		if (player1.equals(soloPlayer))
		{
			playerScores.add(new PlayerScore(gameData[1], player1, Position.START_GAME));
			playerScores.add(new PlayerScore(gameData[2], player2, Position.SECOND));
			playerScores.add(new PlayerScore(gameData[3], player3, Position.THIRD));
			playerScores.add(new PlayerScore(gameData[4], player4, Position.LAST));
		} else if (player2.equals(soloPlayer))
		{
			playerScores.add(new PlayerScore(gameData[1], player1, Position.LAST));
			playerScores.add(new PlayerScore(gameData[2], player2, Position.START_GAME));
			playerScores.add(new PlayerScore(gameData[3], player3, Position.SECOND));
			playerScores.add(new PlayerScore(gameData[4], player4, Position.THIRD));
		} else if (player3.equals(soloPlayer))
		{
			playerScores.add(new PlayerScore(gameData[1], player1, Position.THIRD));
			playerScores.add(new PlayerScore(gameData[2], player2, Position.LAST));
			playerScores.add(new PlayerScore(gameData[3], player3, Position.START_GAME));
			playerScores.add(new PlayerScore(gameData[4], player4, Position.SECOND));
		} else if (player4.equals(soloPlayer))
		{
			playerScores.add(new PlayerScore(gameData[1], player1, Position.SECOND));
			playerScores.add(new PlayerScore(gameData[2], player2, Position.THIRD));
			playerScores.add(new PlayerScore(gameData[3], player3, Position.LAST));
			playerScores.add(new PlayerScore(gameData[4], player4, Position.START_GAME));
		} else
		{

			if (dealer.equals(player1))
			{
				playerScores.add(new PlayerScore(gameData[1], player1, Position.LAST));
				playerScores.add(new PlayerScore(gameData[2], player2, Position.START_GAME));
				playerScores.add(new PlayerScore(gameData[3], player3, Position.SECOND));
				playerScores.add(new PlayerScore(gameData[4], player4, Position.THIRD));
			} else if (dealer.equals(player2))
			{
				playerScores.add(new PlayerScore(gameData[1], player1, Position.THIRD));
				playerScores.add(new PlayerScore(gameData[2], player2, Position.LAST));
				playerScores.add(new PlayerScore(gameData[3], player3, Position.START_GAME));
				playerScores.add(new PlayerScore(gameData[4], player4, Position.SECOND));
			} else if (dealer.equals(player3))
			{
				playerScores.add(new PlayerScore(gameData[1], player1, Position.SECOND));
				playerScores.add(new PlayerScore(gameData[2], player2, Position.THIRD));
				playerScores.add(new PlayerScore(gameData[3], player3, Position.LAST));
				playerScores.add(new PlayerScore(gameData[4], player4, Position.START_GAME));
			} else
			{
				playerScores.add(new PlayerScore(gameData[1], player1, Position.START_GAME));
				playerScores.add(new PlayerScore(gameData[2], player2, Position.SECOND));
				playerScores.add(new PlayerScore(gameData[3], player3, Position.THIRD));
				playerScores.add(new PlayerScore(gameData[4], player4, Position.LAST));
			}
		}
		playerScores.sort(Comparator.comparing(PlayerScore::getPosition));
		return playerScores;
	}

	private Player mapPlayerByName(String name, Player... players)
	{
		for (Player player : players)
		{
			if (player.getName().equals(name))
			{
				return player;
			}
		}
		return null;
	}

	private List<File> loadFiles(String folderString) throws URISyntaxException
	{
		ClassLoader classLoader = this.getClass().getClassLoader();
        File folder = new File(classLoader.getResource(folderString).getFile());

        System.out.println(folder.getPath());
		System.out.println(folder.listFiles());
		return Arrays.asList(folder.listFiles());
	}

	private LocalDate mapFileNameToDate(String fileName)
	{
		String withoutTail = fileName.substring(0, fileName.length() - 4);
		String[] splitted = withoutTail.split("_");
		return LocalDate.of(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]),
				Integer.parseInt(splitted[2]));
	}

	private Player calcDealer(String name, Player... players) throws InvalidDealerException
	{
		Player dealer = mapPlayerByName(name, players);
		if (dealer != null)
		{
			return dealer;
		}
		throw new InvalidDealerException("Dealername was not found!");
	}

	private String calcSoloPlayer(String[] gameData)
	{
		if (gameData.length > 5)
		{
			return gameData[5];
		}
		return null;
	}

}
