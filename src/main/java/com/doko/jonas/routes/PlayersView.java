package com.doko.jonas.routes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.doko.jonas.dataTypes.Player;
import com.doko.jonas.service.DataService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "playersView", layout = MainView.class)
@PageTitle("Players")
@CssImport("./styles/shared-styles.css")
public class PlayersView extends VerticalLayout
{

	private static final long serialVersionUID = 1L;
	@Inject
	private DataService dataService;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		List<Player> players = dataService.getAllPlayers();
		Grid<Player> playerGrid = new Grid<>(Player.class);
		playerGrid.setItems(players);
		playerGrid
				.addItemClickListener(e -> playerGrid.getUI()
						.ifPresent(ui -> ui.navigate(PlayerView.class, e.getItem().getName())));

		addClassName("centered-content");
		add(playerGrid);
	}

}
