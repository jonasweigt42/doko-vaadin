package com.doko.jonas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.doko.jonas.dataCollect.PlayerPool;
import com.doko.jonas.dataTypes.Game;
import com.doko.jonas.dataTypes.Player;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a simple label element and a template element.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow with CDI", shortName = "Project Base")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout
{

	private static final long serialVersionUID = 1L;

	@Inject
	private DataService dataService;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		Grid<Game> gameGrid = new Grid<>(Game.class);
		gameGrid.setItems(dataService.getAllGames());

		Notification.show("Daten für " + dataService.getGameCount() + " Spiele geladen...");

		List<Player> players = PlayerPool.getAllPlayers();
		Grid<Player> playerGrid = new Grid<>(Player.class);
		playerGrid.setItems(players);

		addClassName("centered-content");

		add(playerGrid, gameGrid);
	}

}
